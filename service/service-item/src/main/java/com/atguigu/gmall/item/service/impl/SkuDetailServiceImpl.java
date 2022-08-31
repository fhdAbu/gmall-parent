package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.item.feign.skuDetailFeignClient;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import io.netty.util.concurrent.CompleteFuture;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item.service.impl
 * @Author: FHD
 * @CreateTime: 2022-08-27  15:36
 * @Description:
 * @Version: 2.1
 */
@Service
public class SkuDetailServiceImpl implements SkuDetailService {
    @Autowired
    skuDetailFeignClient skuDetailFeignClient;
    private Map<Long,SkuDetailTo> skuCache =new ConcurrentHashMap<>();
    /**
     * 可配置的线程池,可自动注入
     */
    @Autowired
    ThreadPoolExecutor executor;
    @Autowired
    StringRedisTemplate redisTemplate;

    //使用本地缓存
    public SkuDetailTo getSkuDetailFromRpc(Long skuId) {
        SkuDetailTo detailTo = new SkuDetailTo();


//        executor.submit(()->{});
        //CompletableFuture.runAsync() //CompletableFuture<Void>  启动一个下面不用他返回结果的异步任务
        //CompletableFuture.supplyAsync()//CompletableFuture<U>   启动一个下面用他返回结果的异步任务

        /*1.查基本信息*/
        CompletableFuture<SkuInfo> skuInfoFuture = CompletableFuture.supplyAsync(() -> {
            Result<SkuInfo> result = skuDetailFeignClient.getSkuInfo(skuId);
            SkuInfo skuInfo = result.getData();
            detailTo.setSkuInfo(skuInfo);
            return skuInfo;
        }, executor);
        /*2.查商品图片  thenAccept:接受上一步返回结果*/
        CompletableFuture<Void> imageFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            Result<List<SkuImage>> skuImages = skuDetailFeignClient.getSkuImages(skuId);
            skuInfo.setSkuImageList(skuImages.getData());
        }, executor);

        /*3.查商品实时价格 vo*/
        CompletableFuture<Void> priceFuture = CompletableFuture.runAsync(() -> {
            Result<BigDecimal> Price = skuDetailFeignClient.getSku1010Price(skuId);
            detailTo.setPrice(Price.getData());
        }, executor);

        /*4.查销售属性名和值*/
        CompletableFuture<Void> saleAttrFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            Result<List<SpuSaleAttr>> saleAttrValues = skuDetailFeignClient.getSkuSaleAttrValues(skuId, skuInfo.getSpuId());
            detailTo.setSpuSaleAttrList(saleAttrValues.getData());
        }, executor);


        /*5.查sku组合*/
        CompletableFuture<Void> skuValueFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            Result<String> skuValueJson = skuDetailFeignClient.getSkuValueJson(skuId);
            detailTo.setValueSkuJson(skuValueJson.getData());
        }, executor);


        /*6.查分类*/
        CompletableFuture<Void> categoryFuture = skuInfoFuture.thenAccept(skuInfo -> {
            Result<CategoryViewTo> categoryView = skuDetailFeignClient.getCategoryView(skuInfo.getCategory3Id());
            detailTo.setCategoryView(categoryView.getData());
        });


        // 1.completableFuture  异步编排
        /**
         * 启动一个异步任务有多种办法
         * 1.new thread().start()
         * 2.Runnable  new Thread(runnable).start();
         * 3.callable 带结果 FutureTask
         * 4.线程池 executor.submit()->{}   executor.execute(()->{});
         * 5. 异步编排  CompletableFuture.runAsync()
         *   CompletableFuture 启动异步任务
         */
        CompletableFuture.
                allOf( imageFuture, priceFuture, saleAttrFuture, skuValueFuture, categoryFuture).
                join();
        return detailTo;
    }

    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        //1.看缓存中有没有 sku:info : 50
        String jsonStr = redisTemplate.opsForValue().get("sku:info" + skuId);
        if ("x".equals(jsonStr)){
            //说明以前查过,只不过数据库没有记录,为了避免再次回源,缓存了一个占位符
            return null;
        }
        if(StringUtil.isEmpty(jsonStr)){
            //2.redis没有缓存数据
            //2.1回源之前.判断redis中保存的sku的id集合,有没有这个id;
            SkuDetailTo fromRpc = getSkuDetailFromRpc(skuId);
            //2.2放入缓存[查到的对象转为json字符串保存到redis]
            String cacheJson = "x";
            if(fromRpc !=null){
                cacheJson = Jsons.toStr(fromRpc);
                redisTemplate.opsForValue().set("sku:info"+skuId, cacheJson,7,TimeUnit.DAYS);
            }else {
                redisTemplate.opsForValue().set("sku:info"+skuId, cacheJson,30,TimeUnit.MINUTES);
            }
            return fromRpc;
        }
        //缓存中有数据  把json转成指定的对象
       SkuDetailTo skuDetailTo = Jsons.toObj(jsonStr,SkuDetailTo.class);
        return skuDetailTo;
    }
//    @Override
//    public SkuDetailTo getSkuDetail(Long skuId){
//        //1.先看缓存
//        SkuDetailTo cacheData = skuCache.get(skuId);
//        //2.判断
//        if(cacheData==null){
//            //缓存没有,真正查询[回源(回到数据源头真正检索)]{提高缓存的命中率}
//            /**
//             * 1. -  0/1:  0%
//             * 2. -  1/2:  50%
//             * n. -  n-1/n:  无限接近100%
//             * 缓存命中率提升到100%:预缓存机制
//             */
//            SkuDetailTo fromRpc = getSkuDetailFromRpc(skuId);
//            skuCache.put(skuId,fromRpc);
//            return  fromRpc;
//        }
//        //缓存有
//        return cacheData;
//    }

}

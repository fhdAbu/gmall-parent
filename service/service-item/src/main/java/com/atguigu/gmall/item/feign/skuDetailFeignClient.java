package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item.feign
 * @Author: FHD
 * @CreateTime: 2022-08-27  17:01
 * @Description:
 * @Version: 2.1
 */
@FeignClient("service-product")
@RequestMapping("/api/inner/rpc/product")
public interface skuDetailFeignClient {

    /*@GetMapping("/skuDetail/{skuId}")
    public Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId")Long skuId);*/
    /*1.查询sku基本信息*/
    @GetMapping("skuDetail/info/{skuId}")
    Result<SkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId);

    /*2查询sku的图片信息*/
    @GetMapping("skuDetail/images/{skuId}")
    Result <List<SkuImage>> getSkuImages(@PathVariable("skuId") Long skuId);

    /*3查询实时价格*/
    @GetMapping("skuDetail/price/{skuId}")
    Result<BigDecimal> getSku1010Price(@PathVariable("skuId") Long skuId);

    /*4.查询sku对应的spu所有的销售属性名和值,传入skuId是为了标记当前sku是哪个*/
    @GetMapping("skuDetail/saleAttrAndValues/{skuId}/{spuId}")
    Result<List<SpuSaleAttr>> getSkuSaleAttrValues(@PathVariable Long skuId,
                                                          @PathVariable Long spuId);

    /*5.查询sku组合的valueJson*/
    @GetMapping("skuDetail/valueJson/{spuId}")
    Result<String> getSkuValueJson(@PathVariable("spuId") Long spuId);

    /*6.查分类*/
//    @GetMapping("skuDetail/categoryView/{c3id}")
//    Result getCategoryView(Long c3id);
    // java.util.LinkedHashMap cannot be cast to com.atguigu.gmall.model.to.CategoryViewTo
    @GetMapping("skuDetail/categoryView/{c3id}")
    public Result<CategoryViewTo> getCategoryView(@PathVariable("c3id")Long c3id);
}

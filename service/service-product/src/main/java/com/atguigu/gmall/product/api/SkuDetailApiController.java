package com.atguigu.gmall.product.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.api
 * @Author: FHD
 * @CreateTime: 2022-08-27  16:21
 * @Description:
 * @Version: 2.1
 */

/**
 * 商品详情数据库层的操作
 */
@RestController
@RequestMapping("/api/inner/rpc/product")
public class SkuDetailApiController {
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    @Autowired
    BaseCategory3Service baseCategory3Service;
//    @GetMapping("/skuDetail/{skuId}")
//    public Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId")Long skuId){
//        SkuDetailTo skuDetailTo = skuInfoService.getSkuDetail(skuId);
//        return Result.ok(skuDetailTo);
//    }

    // "java.lang.ClassCastException:
    // java.util.LinkedHashMap cannot be cast to com.atguigu.gmall.model.to.Ca... (
    /*1.查询sku基本信息*/
    @GetMapping("skuDetail/info/{skuId}")
    public Result<SkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId){
        SkuInfo skuInfo = skuInfoService.getDetailInfo(skuId);
        return Result.ok(skuInfo);
    }
    /*2查询sku的图片信息*/
    @GetMapping("skuDetail/images/{skuId}")
    public Result <List<SkuImage>> getSkuImages(@PathVariable("skuId") Long skuId){
        List<SkuImage> images = skuInfoService.getDetailSkuImage(skuId);
        return Result.ok(images);
    }
    /*3查询实时价格*/
    @GetMapping("skuDetail/price/{skuId}")
    public Result<BigDecimal> getSku1010Price(@PathVariable("skuId") Long skuId){
        skuInfoService.get1010Price(skuId);
        return Result.ok();
    }
    /*4.查询sku对应的spu所有的销售属性名和值,传入skuId是为了标记当前sku是哪个*/
    @GetMapping("skuDetail/saleAttrAndValues/{skuId}/{spuId}")
    public Result<List<SpuSaleAttr>> getSkuSaleAttrValues(@PathVariable Long skuId,
                                       @PathVariable Long spuId){
        List<SpuSaleAttr> saleAttrList = spuSaleAttrService.
                getSaleAttrAndValueMarkSku(spuId,skuId);
        return Result.ok(saleAttrList);
    }
    /*查询sku组合的valueJson*/
    @GetMapping("skuDetail/valueJson/{spuId}")
    public Result<String> getSkuValueJson(@PathVariable("spuId") Long spuId){
        String valueJson = spuSaleAttrService.getAllSaleSkuSaleAttrValueJson(spuId);
        return Result.ok(valueJson);
    }
    /*查分类*/
    //java.util.LinkedHashMap cannot be cast to com.atguigu.gmall.model.to.CategoryViewTo
    @GetMapping("skuDetail/categoryView/{c3id}")
    public Result<CategoryViewTo> getCategoryView(@PathVariable("c3id")Long c3id){
        CategoryViewTo categoryViewTo = baseCategory3Service.getCategoryView(c3id);
        return Result.ok(categoryViewTo);
    }
}

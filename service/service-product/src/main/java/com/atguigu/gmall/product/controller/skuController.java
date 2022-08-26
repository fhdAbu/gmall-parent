package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.product.service.SkuAttrValueService;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.controller
 * @Author: FHD
 * @CreateTime: 2022-08-26  16:05
 * @Description:
 * @Version: 2.1
 * sku管理
 */
@RestController
@RequestMapping("/admin/product")
public class skuController {
    @Autowired
    SkuInfoService skuInfoService;
    /**
     * sku分页查询
     * @param pn
     * @param ps
     * @return
     */
    @GetMapping("/list/{pn}/{ps}")
    public Result getSkuList(@PathVariable("pn")Long pn,
                             @PathVariable("ps")Long ps){

        Page<SkuInfo>page = new Page<>(pn,ps);
        Page<SkuInfo> result = skuInfoService.page(page);
        return Result.ok(result);
    }
    /**
     * /admin/product/saveSkuInfo
     * 接前端的数据,可以使用逆向当时生成vo
     */
    @PostMapping("/saveSkuInfo")
    public Result SaveSku(@RequestBody SkuInfo info){
    //sku的大保存
        skuInfoService.saveSkuInfo(info);
    return Result.ok();
    }

    /**
     * 商品下架
     * @param skuId
     * @return
     */
    ///admin/product/cancelSale/40
    @GetMapping("/cancelSale/{skuId}")
    public Result cancelSale(@PathVariable("skuId") Long skuId){
        skuInfoService.cancelSale(skuId);
        return Result.ok();
    }

    /**
     * 商品上架
     * @param skuId
     * @return
     */
    @GetMapping("/onSale/{skuId}")
    public Result onSale(@PathVariable("skuId") Long skuId){
        skuInfoService.onSale(skuId);
        return Result.ok();
    }
}

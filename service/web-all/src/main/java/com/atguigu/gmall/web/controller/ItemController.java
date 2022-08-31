package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.web.feign.SkuDetailFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.web.controller
 * @Author: FHD
 * @CreateTime: 2022-08-27  14:39
 * @Description:
 * @Version: 2.1
 */

/**
 * 商品详情
 */
@Controller
public class ItemController {

    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;
    @GetMapping("/{skuId}.html")
    public String item(@PathVariable("skuId")Long skuId,
                       Model model){

//        SkuDetailTo skuDetailTo = skuFeignClient.getSkuDetail(skuId);
        Result<SkuDetailTo> result = skuDetailFeignClient.getSkuDetail(skuId);
        if(result.isOk()){
            SkuDetailTo skuDetailTo = result.getData();
            model.addAttribute("categoryView",skuDetailTo.getCategoryView());
            model.addAttribute("skuInfo",skuDetailTo.getSkuInfo());
            model.addAttribute("price",skuDetailTo.getSkuInfo().getPrice());
            model.addAttribute("spuSaleAttrList",skuDetailTo.getSpuSaleAttrList());
            model.addAttribute("valuesSkuJson",skuDetailTo.getValueSkuJson());
        }
        return "item/index";
    }
}

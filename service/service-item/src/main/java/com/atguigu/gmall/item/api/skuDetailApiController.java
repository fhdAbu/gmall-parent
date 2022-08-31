package com.atguigu.gmall.item.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.to.SkuDetailTo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item.api
 * @Author: FHD
 * @CreateTime: 2022-08-27  15:19
 * @Description:
 * @Version: 2.1
 */
@RestController
@Api(tags = "三级分类的RPC接口")
@RequestMapping("api/inner/rpc/item")
public class skuDetailApiController {
    @Autowired
    SkuDetailService skuDetailService;

    @GetMapping("/skuDetail/{skuId}")
    public Result<SkuDetailTo> getSkuDetail(@PathVariable("skuId") Long skuId){
        //TODO 商品的详情
        SkuDetailTo skuDetailTo =skuDetailService.getSkuDetail(skuId);
        return Result.ok(skuDetailTo);
    }
}

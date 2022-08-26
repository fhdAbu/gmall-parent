package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.service.SpuSaleAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.controller
 * @Author: FHD
 * @CreateTime: 2022-08-25  15:43
 * @Description:
 * @Version: 2.1
 */

@RestController
@RequestMapping("/admin/product")
public class SpuMangerController {
    @Autowired
    SpuInfoService spuInfoService;
    @Autowired
    SpuImageService spuImageService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    /**
     * 分页获取spu
     *  pathVariable:路径变量
     *  requestParam: 请求参数(请求体中的某个数据)
     *  requestBody: 请求体参数(请求体的所有数据拿来)
     *  什么是? 无论是? 以后的数据还是请求体的数据,都叫请求参数
     *  http://192.168.200.100:9000/admin/product/1/10?category3Id=2
     *  ? 以前的是请求路径  @pathVariable
     *    以后的请求参数 : @requestParam
     *    如果是post请求,请求参数既可以放到url ?以后 ,也可以放在请求体
     *                  -@requestparam : ?以后和请求体都能取
     *     如果是get请求,请求参数需要放到url后面
     *         -@requestParam:   ?以后和请求体都能取
     *
     *      发一个请求:
     *      请求首行:     \n  GET  http://xxxxx?param
     *      请求头:       \n  Context-Type: xxx, ***
     *      请求体:       \n  任意数据
     *      负载:       请求参数(?后面和请求体) @ requestParam工作的地方
     */
    @GetMapping("/{pn}/{ps}")
    public Result getSpuPage(@PathVariable("pn")Long pn,
                             @PathVariable("ps")Long ps,
                             @RequestParam("category3Id")Long category3Id ){
        Page<SpuInfo>page = new Page<>(pn,ps);
        QueryWrapper<SpuInfo>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category3_id",category3Id);
        //分页查询
        Page<SpuInfo> result = spuInfoService.page(page, queryWrapper);
        return Result.ok(result);

    }

    /**
     * 获取所有品牌
     */
    @GetMapping("/baseTrademark/getTrademarkList")
    public  Result getTrademarkList(){
        List<SpuInfo> list = spuInfoService.list();
        return Result.ok(list);
    }
    //spu定义这种商品的所有销售属性(颜色[1,2,3],版本[4,5,6],套餐[7,8,9])
    //sku只是spu当前定义的所有销售属性中的一个精确组合
    ///admin/product/saveSpuInfo

    /**
     * 保存spu信息
     * @return
     */
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo info){
        spuInfoService.saveSpuInfo(info);
        return Result.ok();
    }

    ///admin/product/spuSaleAttrList/28
    @GetMapping("/spuImageList/{spuId}")
    public Result spuImageList(@PathVariable("spuId") Long spuId){
        QueryWrapper<SpuImage> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id",spuId);
        List<SpuImage> spuImages = spuImageService.list(wrapper);
        return Result.ok(spuImages);
    }

    /**
     * 查出指定spu当时定义的所有销售属性的名和值
     * @param spuId
     * @return
     */
    ///admin/product/spuSaleAttrList/28
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable("spuId")Long spuId){

       List<SpuSaleAttr> spuSaleAttrs =  spuSaleAttrService.getSaleAttrAndValueBySpuId(spuId);
       return Result.ok(spuSaleAttrs);
    }
}

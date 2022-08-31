package com.atguigu.gmall.model.to;

import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.model.to
 * @Author: FHD
 * @CreateTime: 2022-08-27  15:12
 * @Description:
 * @Version: 2.1
 */
@Data
public class SkuDetailTo {
    //当前sku所属的商品分类信息
    private CategoryViewTo categoryView; //商品的分类信息
    private SkuInfo skuInfo; //商品的基本信息
    private List<SpuSaleAttr> spuSaleAttrList; //商品的所有销售属性列表
    private String valueSkuJson;  //
    private BigDecimal price; //实时价格
}

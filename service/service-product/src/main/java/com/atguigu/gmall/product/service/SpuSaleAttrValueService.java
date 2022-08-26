package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Luckyfan
* @description 针对表【spu_sale_attr_value(spu销售属性值)】的数据库操作Service
* @createDate 2022-08-23 16:19:12
*/
public interface SpuSaleAttrValueService extends IService<SpuSaleAttrValue> {
    /**
     * 根据spuId查询对应的所有销售属性名和值
     * @param spuId
     * @return
     */
}

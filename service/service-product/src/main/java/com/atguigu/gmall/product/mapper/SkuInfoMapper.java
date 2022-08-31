package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
* @author Luckyfan
* @description 针对表【sku_info(库存单元表)】的数据库操作Mapper
* @createDate 2022-08-23 16:19:12
* @Entity com.atguigu.gmall.product.domain.SkuInfo
*/
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {
    /**
     * 更新sku_isSale字段
     * @param skuId
     * @param sale
     */
    void updateIsSale(@Param("skuId") Long skuId, @Param("sale") int sale);

    /**
     * 查询出所有sku的销售属性值组合
     * @param skuId
     * @return
     */
    BigDecimal getRealPrice(@Param("skuId") Long skuId);
}





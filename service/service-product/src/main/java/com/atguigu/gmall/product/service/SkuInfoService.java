package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
* @author Luckyfan
* @description 针对表【sku_info(库存单元表)】的数据库操作Service
* @createDate 2022-08-23 16:19:12
*/
public interface SkuInfoService extends IService<SkuInfo> {

    void saveSkuInfo(SkuInfo info);

    void cancelSale(Long skuId);

    void onSale(Long skuId);

    /**
     * 获取sku商品详情数据的
     * @return
     */
    SkuDetailTo getSkuDetail(Long skuId);
    /**
     * 获取商品实时价格
     */
    BigDecimal get1010Price(Long skuId);

    /**
     * 获取skuInfo信息
     * @param skuId
     * @return
     */
    SkuInfo getDetailInfo(Long skuId);

    /**
     * 获取sku的所有图片
     * @param skuId
     * @return
     */
    List<SkuImage> getDetailSkuImage(Long skuId);
}

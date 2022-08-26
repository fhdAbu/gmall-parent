package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SkuAttrValue;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SkuSaleAttrValue;
import com.atguigu.gmall.product.service.SkuAttrValueService;
import com.atguigu.gmall.product.service.SkuImageService;
import com.atguigu.gmall.product.service.SkuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Luckyfan
* @description 针对表【sku_info(库存单元表)】的数据库操作Service实现
* @createDate 2022-08-23 16:19:12
*/
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
    implements SkuInfoService{
    @Autowired
    SkuImageService skuImageService;
    @Autowired
    SkuAttrValueService skuAttrValueService;
    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    SkuInfoMapper skuInfoMapper;
    @Transactional
    @Override
    public void saveSkuInfo(SkuInfo info) {
        //1.sku基本信息保存到sku_info
        save(info);
        Long skuId = info.getId();
        //2.sku图片信息保存到sku_image
        for (SkuImage skuImage : info.getSkuImageList()) {
            skuImage.setSkuId(skuId);
        }
        skuImageService.saveBatch( info.getSkuImageList());

        //3.sku的平台属性名和值的关系保存到sku_attr_value
        List<SkuAttrValue> attrValueList = info.getSkuAttrValueList();
        for (SkuAttrValue attrValue : attrValueList) {
                attrValue.setSkuId(skuId);
        }
        skuAttrValueService.saveBatch(attrValueList);
        //3.sku的销售属性名和值的关系保存到sku_sale_attr_value
        List<SkuSaleAttrValue> saleAttrValueList = info.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue saleAttrValue : saleAttrValueList) {
            saleAttrValue.setSaleAttrValueId(skuId);
            saleAttrValue.setSpuId(info.getSpuId());
        }
        skuSaleAttrValueService.saveBatch(saleAttrValueList);
    }

    @Override
    public void cancelSale(Long skuId) {
        //改数据库sku_info 这个skuId的is_sale"  1:上架  0:下架
        skuInfoMapper.updateIsSale(skuId,0);
        //TODO 从es中删除这个商品
    }

    @Override
    public void onSale(Long skuId) {
        skuInfoMapper.updateIsSale(skuId,1);

        //TODO 从es中保存这个商品
    }
}





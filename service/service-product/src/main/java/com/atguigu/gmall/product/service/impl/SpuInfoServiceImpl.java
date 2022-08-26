package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.mapper.SpuImageMapper;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.mapper.SpuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Luckyfan
* @description 针对表【spu_info(商品表)】的数据库操作Service实现
* @createDate 2022-08-23 16:19:12
*/
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo>
    implements SpuInfoService{
    @Autowired
    SpuInfoMapper spuInfoMapper;
    @Autowired
    SpuImageService spuImageService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    @Autowired
    SpuSaleAttrValueService spuSaleAttrValueService;

    @Transactional
    @Override
    public void saveSpuInfo(SpuInfo info) {
        //1.把spu的基本信息保存到spu-info表中
        spuInfoMapper.insert(info);
        Long id = info.getId(); //闹到spu保存后的自增id
        //2.把spu图片保存到spu_image表中
        List<SpuImage> spuImageList = info.getSpuImageList();
        for (SpuImage image : spuImageList){
            //回填spuId
            image.setSpuId(id);
        }
        //批量保存图片
        spuImageService.saveBatch(spuImageList);

        //3.保存销售属性名 到spu_sale_attr
        List<SpuSaleAttr> attrNameList = info.getSpuSaleAttrList();
        for (SpuSaleAttr  attr : attrNameList){
            //回填spuId
            attr.setSpuId(id);
            //4.拿到这个销售名对应的所有属性值
            List<SpuSaleAttrValue> valueList = attr.getSpuSaleAttrValueList();
            for(SpuSaleAttrValue attrValue:valueList){
                attrValue.setSpuId(id);
                String saleAttrName = attr.getSaleAttrName();
                attrValue.setSaleAttrValueName(saleAttrName);
            }
            //保存销售属性值
            spuSaleAttrValueService.saveBatch(valueList);
        }
        //保存到数据库
        spuSaleAttrService.saveBatch(attrNameList);


    }
}





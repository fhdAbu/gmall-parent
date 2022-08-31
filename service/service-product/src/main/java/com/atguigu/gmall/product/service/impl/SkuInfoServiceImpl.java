package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Autowired
    BaseCategory3Mapper baseCategory3Mapper;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
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
    @Deprecated
    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        SkuDetailTo detailTo = new SkuDetailTo();
        //首先查询到skuInfo
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        Long c3id = skuInfo.getCategory3Id();
        //TODO 准备查询所有需要的数据
        /**
         * TODO 远程查询出商品的详细信息  categoryView/skuInfo/spuSaleAttrList
         * 商品的详情包含以下内容
         * 4.商品(sku)所属的spu当时定义的所有属性名值组合,并标识出来 sku_sale_attr_value
         * 5.商品(sku)类似推荐
         * 6.商品(sku)介绍[所属的spu的海报]  spu_poster
         * 7.商品(sku)的规格参数  sku_attr_value
         * 8.商品(sku)的售后/评论    相关的表
         */
        /*TODO 1.商品(sku)所属的完整分类信息  base-category1  base-category2 base-category3*/
        CategoryViewTo categoryViewTo = baseCategory3Mapper.getCategoryView(c3id);
        detailTo.setCategoryView(categoryViewTo);

        /*TODO 2.商品(sku)的基本信息[价格,重量,名字.....] sku-info*/
        detailTo.setSkuInfo(skuInfo);
        /*TODO 2.1实时价格查询*/
        BigDecimal price = get1010Price(skuId);
        detailTo.setPrice(price);
        /*TODO 3.商品(sku)的图片  sku-image*/
        List<SkuImage>imageList =  skuImageService.getSkuImage(skuId);
        skuInfo.setSkuImageList(imageList);

        /*TODO 4.商品(sku)所属的spu当时定义的所有属性名值组合,并标识出来 sku_sale_attr_value*/
        //查询出当前sku对应的spu定义的所有销售属性名和值(固定好顺序)并且标记好当前sku属于哪一种组合
        List<SpuSaleAttr> saleAttrList = spuSaleAttrService.
                getSaleAttrAndValueMarkSku(skuInfo.getSpuId(),skuId);
        detailTo.setSpuSaleAttrList(saleAttrList);
        /*TODO 5.商品(sku)类似推荐*/
        Long spuId = skuInfo.getSpuId();
        String valueJson = spuSaleAttrService.getAllSaleSkuSaleAttrValueJson(spuId);
        detailTo.setValueSkuJson(valueJson);
        return detailTo;
    }

    @Override
    public BigDecimal get1010Price(Long skuId) {
        BigDecimal price = skuInfoMapper.getRealPrice(skuId);
        return price;
    }

    @Override
    public SkuInfo getDetailInfo(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        return skuInfo;
    }

    @Override
    public List<SkuImage> getDetailSkuImage(Long skuId) {
        List<SkuImage>imageList =  skuImageService.getSkuImage(skuId);
        return imageList;
    }
}





package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.product.mapper.BaseCategory2Mapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Luckyfan
* @description 针对表【base_category3(三级分类表)】的数据库操作Service实现
* @createDate 2022-08-23 02:02:29
*/
@Service
public class BaseCategory3ServiceImpl extends ServiceImpl<BaseCategory3Mapper, BaseCategory3>
    implements BaseCategory3Service{
    @Autowired
    BaseCategory3Mapper baseCategory3Mapper;
    @Override
    public List<BaseCategory3> getCategory2Child(Long c2id) {
        //selece * from base_category3 where category2_id = ?
        QueryWrapper<BaseCategory3>queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("category2_id", c2id);
        List<BaseCategory3> list = baseCategory3Mapper.selectList(queryWrapper);
        return list;
    }

    /**
     * 查询分类
     * @param c3id
     * @return
     */
    @Override
    public CategoryViewTo getCategoryView(Long c3id) {
        CategoryViewTo categoryViewTo = baseCategory3Mapper.getCategoryView(c3id);
        return categoryViewTo;
    }

}





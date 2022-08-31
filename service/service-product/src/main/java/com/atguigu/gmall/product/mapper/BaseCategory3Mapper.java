package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Luckyfan
* @description 针对表【base_category3(三级分类表)】的数据库操作Mapper
* @createDate 2022-08-23 02:02:29
* @Entity com.atguigu.gmall.product.domain.BaseCategory3
*/
public interface BaseCategory3Mapper extends BaseMapper<BaseCategory3> {
    /**
     * 根据三级分类id查询出对应的二级以及一级父分类的信息
     * @param c3id
     * @return
     */
    CategoryViewTo getCategoryView(@Param("c3id") Long c3id);
}





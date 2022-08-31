package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Luckyfan
* @description 针对表【base_category3(三级分类表)】的数据库操作Service
* @createDate 2022-08-23 02:02:29
*/
public interface BaseCategory3Service extends IService<BaseCategory3> {
    /**
     * 获取某个二级分类下的三级分类
     * @param c2id
     * @return
     */
    List<BaseCategory3> getCategory2Child(Long c2id);

    /**
     * 根据三级分类id查询寻出整个精确的分类路径
     * @param c3id
     * @return
     */
    CategoryViewTo getCategoryView(Long c3id);
}

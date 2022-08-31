package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Luckyfan
* @description 针对表【base_category2(二级分类表)】的数据库操作Service
* @createDate 2022-08-23 02:02:29
*/
public interface BaseCategory2Service extends IService<BaseCategory2> {
    /**
     * 查询1级分类下边的2级分类
     * @param c1id
     * @return
     */
    List<BaseCategory2> getCategory1Child(Long c1id);

    /**
     * 查询所有分类以及它下面的子分类,并组装成树形结构
     * @return
     */
    List<CategoryTreeTo> getAllCategoryWithTree();
}

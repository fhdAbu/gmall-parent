package com.atguigu.gmall.model.to;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.model.to
 * @Author: FHD
 * @CreateTime: 2022-08-27  09:34
 * @Description:
 * @Version: 2.1
 */

import lombok.Data;

import java.util.List;

/**
 * DDD: 领域驱动设计  Domain-Driven Design
 *
 * 三级分类树形结构
 * 支持无限层级
 * 当前项目只有三级
 */
@Data
public class CategoryTreeTo {
    private Long categoryId;
    private String categoryName;
    private List<CategoryTreeTo> categoryChild;//子分类
}

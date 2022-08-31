package com.atguigu.gmall.product.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.api
 * @Author: FHD
 * @CreateTime: 2022-08-27  01:51
 * @Description:
 * @Version: 2.1
 */

/**
 * 分类有关的API
 * 以后所有的调用都是内部接口:命名规范api/inner/rpc
 */
@Api(tags = "三级分类的RPC接口")
@RestController
    @RequestMapping("/api/inner/rpc/product")
public class CategoryApiController {
    @Autowired
    BaseCategory2Service baseCategory2Service;
    /**
     * 查询所有的分类 并分装成树形菜单结构
     * @return
     */
    @ApiOperation("三级分类树形结构查询")
    @GetMapping("/category/tree")
    public Result getAllCategoryWithTree(){
       List<CategoryTreeTo> categoryTreeTos = baseCategory2Service.getAllCategoryWithTree();

        return Result.ok(categoryTreeTos);
    }
}

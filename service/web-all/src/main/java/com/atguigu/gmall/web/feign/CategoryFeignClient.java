package com.atguigu.gmall.web.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.web.feign
 * @Author: FHD
 * @CreateTime: 2022-08-27  13:40
 * @Description:
 * @Version: 2.1
 */
@FeignClient("service-product") //告诉springboot 这是一个远程调用的客户端
//远程调用之前feign会自己要到service-product   真正的地址
public interface CategoryFeignClient {
    /**
     * 给service-product 发送一个get方式的请求方式  路径是 /api/inner/rpc/product/category/tree
     *  拿到远程的响应json结果后转成 Result类型的对象 ,并且返回的类型是List<CategoryTreeTo>
     * @return
     */
//    @GetMapping("/api/inner/rpc/product/category/tree")
//    Result<List<CategoryTreeTo>> getCategoryTree();
    @ApiOperation("三级分类树形结构查询")
    @GetMapping("/api/inner/rpc/product/category/tree")
    Result getAllCategoryWithTree();
}

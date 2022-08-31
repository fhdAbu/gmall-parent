package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.atguigu.gmall.web.feign.CategoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.web.controller
 * @Author: FHD
 * @CreateTime: 2022-08-27  01:34
 * @Description:
 * @Version: 2.1
 */
@Controller
public class IndexController {
    @Autowired
    CategoryFeignClient categoryFeignClient;

    @GetMapping({"/", "/index"})
    public String indexPage(Model model){
        //TODO 查询出所有菜单 封装成一个树形结构的模型
        Result<List<CategoryTreeTo>> categoryTree = categoryFeignClient
                .getAllCategoryWithTree();
        if (categoryTree.isOk()){
            //远程成功  强类型语言
            List<CategoryTreeTo> data = categoryTree.getData();
            model.addAttribute("list",data);
        }


        //  classpath:/templates/ +  index/index  + .html
        return "index/index"; //页面的逻辑视图名
    }
}

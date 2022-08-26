package com.atguigu.gmall.product.controller;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.controller
 * @Author: FHD
 * @CreateTime: 2022-08-22  22:45
 * @Description:
 * @Version: 2.1
 */

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory1Service;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类的请求处理器
 *
 * 前后分离:前端发送请求,后台处理好后响应JSON数据
 * 所有的请求全部返回 Result 对象的json,所有携带的数据全部放在Result的data属性中即可
 */
@RequestMapping("/admin/product")//抽取公共路径
@RestController  //下边两个的二合一
//@Controller  //代表这个类是来接受请求的
//@ResponseBody //所有的响应数据都直接写给浏览器(如果是对象写成json,如果是文本就写成普通文本字符串)
public class CategoryController {
    @Autowired
    BaseCategory1Service baseCategory1Service;
    @Autowired
    BaseCategory2Service baseCategory2Service;
    @Autowired
    BaseCategory3Service baseCategory3Service;
    /**
     * 获取所有的一级分类
     */
    @GetMapping("/getCategory1")
    public Result getCategory1(){
//        利用mybatisPlus提供好的CRUD方法,直接查出所有一级分类
        List<BaseCategory1> list = baseCategory1Service.list();
        return Result.ok(list);
    }
    // /admin/product/getCategory2/9
    /**
     * 获取某个一级分类下边所有的二级分类
     * @param  c1id 传入一个一级分类id
     */
    @GetMapping("/getCategory2/{c1id}")
    public Result getCategory2(@PathVariable("c1id")Long c1id){
        //查询出父分类id为c1id 的所有二级分类
        List<BaseCategory2> list = baseCategory2Service.getCategory1Child(c1id);
        return Result.ok(list);
    }
    /**
     * 3.获取一级分类下的某个二级分类下的子分类
     */
    @GetMapping("/getCategory3/{c2id}")
    public Result getCategory3(@PathVariable("c2id")Long c2id){
        List<BaseCategory3> list = baseCategory3Service.getCategory2Child(c2id);
        return Result.ok(list);
    }
}

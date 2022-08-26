package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.controller
 * @Author: FHD
 * @CreateTime: 2022-08-24  20:53
 * @Description:
 * @Version: 2.1
 */
@RequestMapping("/admin/product")
@RestController
public class BaseTrademarkController {
    @Autowired
    BaseTrademarkService baseTrademarkService;

    /**
     * 分页查询所有品牌
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/baseTrademark/{page}/{size}")
    public Result baseTrademark(@PathVariable("page")Long page,
                                @PathVariable("size")Long size){
        Page<BaseTrademark> page1 = new Page<>(page,size);
        //分页查询,(分页信息,查询到的记录集合)
        Page<BaseTrademark> pageResult = baseTrademarkService.page(page1);
//        pageResult.getTotal();//代表数据库中有多少记录
//        pageResult.getCurrent();//当前第几页
//        pageResult.getRecords();//查询到的结果
        return Result.ok(pageResult);
    }
    /**
     * /admin/product/baseTrademark/get/3
     * 根据品牌id获取品牌信息
     */


    @GetMapping("/baseTrademark/get/{id}")
    public Result getBaseTrademark(@PathVariable("id")Long id){
        BaseTrademark trademark = baseTrademarkService.getById(id);
        return Result.ok(trademark);
    }
    /**
     * /admin/product/baseTrademark/update
     *   {"id":2,"tmName":"华为max","logoUrl":"http://39.99.159.121:9000/gmall/1623030108745png"}
     */
    @PutMapping("/baseTrademark/update")
    public Result updateBaseTrademark(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }
    /**
     *  保存品牌
     * /admin/product/baseTrademark/save
     * "tmName":"hahaha","logoUrl":"/static/default.jpg"
     */
    @PostMapping("/baseTrademark/save")
    public Result saveBaseTrademark(@RequestBody BaseTrademark trademark){
        baseTrademarkService.save(trademark);
        return Result.ok();
    }
    /**
     *   /admin/product/baseTrademark/remove/13
     */
    @DeleteMapping("/baseTrademark/remove/{id}")
    public Result deleteBaseTrademark(@PathVariable("id")Long id){
        baseTrademarkService.removeById(id);
        return Result.ok();
    }
}

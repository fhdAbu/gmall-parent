package com.atguigu.gmall.item.controller;

import com.atguigu.gmall.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.item.controller
 * @Author: FHD
 * @CreateTime: 2022-08-31  20:15
 * @Description:
 * @Version: 2.1
 */
@RestController
public class SkuBitMapController {
    /**
     * 同步数据库中所有商品id的占位标识
     * @return
     */
    @GetMapping("/sync/skuId/bitmap")
    public Result syncBitMap(){
        //数据库中的所有商品的id查询出来
        //挨个置位 setbit skuids 50 0/1
        return Result.ok();
    }
}

package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.controller
 * @Author: FHD
 * @CreateTime: 2022-08-23  16:12
 * @Description:
 * @Version: 2.1
 */

/**
 * 平台属性相关接口
 */
@RestController
@RequestMapping("/admin/product")
public class baseAttrController {
    @Autowired
    BaseAttrInfoService baseAttrInfoService;
    @Autowired
    BaseAttrValueService baseAttrValueService;
    @GetMapping("/attrInfoList/{c1id}/{c2id}/{c3id}")
    public Result getAttrInfoList(@PathVariable("c1id")Long c1id,
                                  @PathVariable("c2id")Long c2id,
                                  @PathVariable("c3id")Long c3id){
         List<BaseAttrInfo> infos = baseAttrInfoService.getAttrAndInfoByCategoryId(c1id,c2id,c3id);
        return Result.ok(infos);
    }

    /**
     * 保存,修改属性二合一方法
     * 把所有用户输入的数据以json的格式post传入进来
     * 请求体
     * @return
     * 去除前端发送的请求的请求体中的数据@requestBody,
     * 并把这个数据转成(json)转成指定过的baseAttrInfo对象
     * BaseAttrInfo封装前端提交来的所有数据
     */
    @PostMapping("/saveAttrInfo")
    public  Result  saveAttrInfo(@RequestBody BaseAttrInfo info){
        // TODO平台属性的新增
        baseAttrInfoService.saveAttrInfo(info);
        return Result.ok();
    }
    /**
     * 根据平台属性id,获取这个属性的完整信息(属性名,属性值)
     * http://192.168.200.1/admin/product/getAttrValueList/11
     */
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable("attrId")Long attrId){
        List<BaseAttrValue> Values= baseAttrValueService.getAttrValueList(attrId);
        return Result.ok(Values);
    }

}

package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.service.FileUploadService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.config
 * @Author: FHD
 * @CreateTime: 2022-08-25  01:39
 * @Description:
 * @Version: 2.1
 */
@RequestMapping("/admin/product")
@RestController
public class fileUploadController {

    @Autowired
    FileUploadService fileUploadService;
    /**
     * 文件上传功能
     * 前端把文件刘放在哪了 ---->post请求数据在请求体(包含了文件流)
     * 如何接
     *  @requestParam("file")MultipartFile file
     *  @requestPart("file")MultipartFile file 专门处理文件的
     *
     *      各种注解接不同位置上的请求数据
     *          @requestParam  :无论是什么请求, 接请求参数:   用一个pojo把所有数据都接了
     *          @requestPart  : 接请求参数里的文件项
     *          @requestBody  : 接请求体中的所有数据 (json转为pojo)
     *          @parthvariable: 接路径上的动态变量
     *          @RequestHeader: 获取浏览器发送的请求的请求头中的某些值
     *          @cookieValue : 获取浏览器发送的请求的cookie值
     *          如果多个就写数组,否则就写单个对象
     *
     * @return
     */

    @PostMapping("/fileUpload")
    public Result fileUpload(@RequestPart("file")MultipartFile file) throws Exception {
        //TODO 文件上传 怎么上传到 minio ?
        //收到前端的文件流,上传给minio,并返回这个文件在minion中的存储地址
        //以后用这个地址访问,数据库保存的也是这个地址
        String url =  fileUploadService.upload(file);
        return Result.ok(url);
    }
}

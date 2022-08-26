package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.DateUtil;
import com.atguigu.gmall.product.config.minio.MinioProperties;
import com.atguigu.gmall.product.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.service.impl
 * @Author: FHD
 * @CreateTime: 2022-08-25  21:44
 * @Description:
 * @Version: 2.1
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    MinioClient minioClient;
    @Autowired
    MinioProperties minioProperties;
    /**
     * 文件上传
     * @param file
     * @return
     */
    //后便会有全局的异常处理器
    @Override
    public String upload(MultipartFile file) throws Exception {
        //1.创建出一个minio的客户端 minioClient
        //2.判断这个桶是否存在
        boolean gmall = minioClient.bucketExists(minioProperties.getBucketName());
        if(!gmall){
            minioClient.makeBucket(minioProperties.getBucketName());
        }
        //3.给桶内上传文件
        //3.1 objectName:对象名 上传的文件名
        String name = file.getName(); //这是input的name
        //minio中上传两个相同名字的文件,后边的会覆盖前边的,所以设置一个唯一的文件名
        String date = DateUtil.formatDate(new Date());
        String filename = UUID.randomUUID().toString().replace("-","")
                +"_"+file.getOriginalFilename(); //这才是上传的原始的文件名
        InputStream inputStream = file.getInputStream();

        //文件上传的参数
        long size = file.getSize();
        PutObjectOptions options = new PutObjectOptions(size,-1L);
        //文件上传默认都是二进制,必须修改成对相应的图片等类型
        String fileContentType = file.getContentType();
        options.setContentType(fileContentType);
        //文件上传
        minioClient.putObject(
                minioProperties.getBucketName(),
                date+"/"+filename,//自己指定的唯一文件名
                inputStream,
                options);
        //需要返回的是地址值  //192.168.200.100:9000/gmall/filename

        String url = minioProperties.getEndpoint()+"/"+minioProperties.getBucketName()+"/"+date+"/"+filename;
        //优化
        //1.文件名重名覆盖问题
        //2.归档: 以日期作恶日文件夹 2022-12-12/aaa.png
        return url;
    }
}

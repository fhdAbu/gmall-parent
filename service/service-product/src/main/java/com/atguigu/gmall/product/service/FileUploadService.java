package com.atguigu.gmall.product.service;

import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.product.service
 * @Author: FHD
 * @CreateTime: 2022-08-25  21:44
 * @Description:
 * @Version: 2.1
 */

public interface FileUploadService {
    /**
     * 文件上传
     * @param file
     * @return 返回文件在minio中的存储地址
     */
    String upload(MultipartFile file) throws Exception;
}

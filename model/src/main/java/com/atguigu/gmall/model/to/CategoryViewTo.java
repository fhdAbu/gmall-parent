package com.atguigu.gmall.model.to;

import lombok.Data;

/**
 * @BelongsProject: gmall-parent
 * @BelongsPackage: com.atguigu.gmall.model.to
 * @Author: FHD
 * @CreateTime: 2022-08-27  15:31
 * @Description:
 * @Version: 2.1
 */
@Data
public class CategoryViewTo {
    private Long category1Id;
    private String category1Name;
    private Long category2Id;
    private String category2Name;
    private Long category3Id;
    private String category3Name;
}

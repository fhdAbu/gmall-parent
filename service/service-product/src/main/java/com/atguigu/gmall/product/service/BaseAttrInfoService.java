package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Luckyfan
* @description 针对表【base_attr_info(属性表)】的数据库操作Service
* @createDate 2022-08-23 16:19:12
*/
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {
    /**
     * @param c1id 1级分类id
     * @param c2id 2级分类id
     * @param c3id 3级分类id
     * @return
     *
     */
    List<BaseAttrInfo> getAttrAndInfoByCategoryId(Long c1id, Long c2id, Long c3id);

    /**
     * 保存平台属性
     * @param info
     */
    void saveAttrInfo(BaseAttrInfo info);

}

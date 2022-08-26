package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Luckyfan
* @description 针对表【base_attr_info(属性表)】的数据库操作Mapper
* @createDate 2022-08-23 16:19:12
* @Entity com.atguigu.gmall.product.domain.BaseAttrInfo
*/
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {

    List<BaseAttrInfo> getAttrAndInfoByCategoryId(@Param("c1id") Long c1id,
                                                  @Param("c2id") Long c2id,
                                                  @Param("c3id") Long c3id);
}






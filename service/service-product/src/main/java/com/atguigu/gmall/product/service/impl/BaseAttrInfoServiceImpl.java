package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Luckyfan
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2022-08-23 16:19:12
*/
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;
    /**
     *      sql:查询指定分类下的所有属性和值
     *        select bai.*,bav,* from base_attr_info bai where (bai.category_id=2 and bai.category_level=1)
     *         or (bai.category_id=13 and bai.category_level=2) or(bai.category_id=16 and bai.category_level=3)
     *         left join base_attr_value bav on bai.id = bav.attr_id
     *
     * @param c1id 1级分类id
     * @param c2id 2级分类id
     * @param c3id 3级分类id
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAttrAndInfoByCategoryId(Long c1id, Long c2id, Long c3id) {
         List<BaseAttrInfo> infos= baseAttrInfoMapper.getAttrAndInfoByCategoryId(c1id,c2id,c3id);
        return infos;
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo info) {
        //什么情况下是新增
        if(info.getId()==null){
            //进行属性新增操作
            addBaseAttrInfo(info);
        }else {//2.进行属性修改
            updateBaseAttrInfo(info);
        }
    }

    private void updateBaseAttrInfo(BaseAttrInfo info) {
        //修改属性名信息
        baseAttrInfoMapper.updateById(info);
        //修改属性值
        //方法一:  老记录全删,新提交记录全部新增,导致引用失效  (错误)
        // 方法二:  正确做法
        List<BaseAttrValue> valueList = info.getAttrValueList();
        //1.先删除
        //拿到前端提交来的所有属性值先删除
        List<Long>vids = new ArrayList<>();
        for (BaseAttrValue attrValue:valueList){
            Long id = attrValue.getId();
            if(id!=null){
                vids.add(id);
            }
        }
        //删除
        if (vids.size()>0){
            //部分删除
            QueryWrapper<BaseAttrValue> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("attr_id", info.getId());
            deleteWrapper.notIn("id",vids);
            baseAttrValueMapper.delete(deleteWrapper);
        }else {
            //全部删除,把这个属性id下的所有属性值全部删除
            QueryWrapper<BaseAttrValue> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("attr_id", info.getId());
            baseAttrValueMapper.delete(deleteWrapper);
        }

        for(BaseAttrValue attrValues: valueList) {
            if (attrValues.getId()!=null){
                //说明数据库以前有,此次只需要修改即可
                baseAttrValueMapper.updateById(attrValues);
            }
            if (attrValues.getId() == null) {
                    //说明数据库以前没有是新增
                attrValues.setAttrId(info.getId());
                baseAttrValueMapper.insert(attrValues);
            }
        }
    }

    private void addBaseAttrInfo(BaseAttrInfo info) {
        //保存属性名
        baseAttrInfoMapper.insert(info);
        //拿到刚才保存好的属性名的自增id
        Long id = info.getId();
        //保存属性值
        List<BaseAttrValue> attrValueList = info.getAttrValueList();
        for(BaseAttrValue value:attrValueList){
            //回填属性名记录的自增id
            value.setAttrId(id);
            baseAttrValueMapper.insert(value);
        }
    }
}





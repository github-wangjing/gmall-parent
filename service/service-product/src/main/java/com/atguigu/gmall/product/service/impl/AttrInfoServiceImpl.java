package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.product.service.AttrInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttrInfoServiceImpl implements AttrInfoService {
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
   //查询
    @Override
    public List<BaseAttrInfo> attrInfoList(Long category3Id) {
        List<BaseAttrInfo> attrInfoList= baseAttrInfoMapper.selectAttrInfoList(3,category3Id);
//        QueryWrapper<BaseAttrInfo> wrapper = new QueryWrapper<>();
//        wrapper.eq("category_level",3);
//        wrapper.eq("category_id",category3Id);
//        List<BaseAttrInfo> attrInfoList = baseAttrInfoMapper.selectList(wrapper);
//        for (BaseAttrInfo attrInfo : attrInfoList) {
//            Long infoId = attrInfo.getId();
//            QueryWrapper<BaseAttrValue> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("attr_id",infoId);
//            List<BaseAttrValue> valueList = baseAttrValueMapper.selectList(queryWrapper);
//            attrInfo.setAttrValueList(valueList);
//        }
        return attrInfoList;
    }

    //添加属性
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //得到属性id 将添加的属性值内容添加到value
        Long id = baseAttrInfo.getId();
        //如果id为null是添加，否则修改
        if(null!=id&&id>0){
            //更新修改
            QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id",id);
            baseAttrValueMapper.delete(wrapper);
        }else {
            //属性添加
            int insert = baseAttrInfoMapper.insert(baseAttrInfo);
            }
        //取出attrinfo中的属性值
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue attrValue : attrValueList) {
            attrValue.setAttrId(id);
            baseAttrValueMapper.insert(attrValue);
        }




    }
    //修改的数据回显查询
    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId) {
        QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_id",attrId);
        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.selectList(wrapper);
        return baseAttrValueList;
    }
}

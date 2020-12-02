package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.SpuApiService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuApiServiceImpl implements SpuApiService {

    @Autowired
    SpuInfoMapper spuInfoMapper;

    @Autowired
    SpuImageMapper spuImageMapper;

    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Autowired
    BaseSaleAttrMapper baseSaleAttrMapper;

    @Override
    public  IPage<SpuInfo> getSpuList(Page<SpuInfo> spuInfoPage, Long category3Id) {
        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("category3_id",category3Id);
        IPage<SpuInfo> spuInfoIPage = spuInfoMapper.selectPage(spuInfoPage, wrapper);
        return spuInfoIPage;
    }

    @Override
    public List<BaseSaleAttr> baseSaleAttrList() {
        List<BaseSaleAttr> baseSaleAttrList = baseSaleAttrMapper.selectList(null);
        return baseSaleAttrList;
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        spuInfoMapper.insert(spuInfo);
        Long spu_id = spuInfo.getId();
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (null!=spuImageList){
            //遍历
            for (SpuImage spuImage : spuImageList) {
                spuImage.setSpuId(spu_id);
                spuImageMapper.insert(spuImage);
            }
        }
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (null!=spuSaleAttrList){
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                spuSaleAttr.setSpuId(spu_id);
                spuSaleAttrMapper.insert(spuSaleAttr);

                Long baseSaleAttrId = spuSaleAttr.getBaseSaleAttrId();
                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                if (null!=spuSaleAttrValueList){
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        spuSaleAttrValue.setSpuId(spu_id);
                        spuSaleAttrValue.setBaseSaleAttrId(baseSaleAttrId);
                        spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                        spuSaleAttrValueMapper.insert(spuSaleAttrValue);
                    }
                }

            }
        }
    }
    //sku添加页面的图片查询
    @Override
    public List<SpuImage> spuImageList(Long spuId) {
        QueryWrapper<SpuImage> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id",spuId);
        List<SpuImage> spuImageList = spuImageMapper.selectList(wrapper);
        return spuImageList;
    }

    //sku添加页面的销售属性查询
    @Override
    public List<SpuSaleAttr> spuSaleAttrList(Long spuId) {
        QueryWrapper<SpuSaleAttr> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id",spuId);
        List<SpuSaleAttr> spuSaleAttrList = spuSaleAttrMapper.selectList(wrapper);

        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            QueryWrapper<SpuSaleAttrValue> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("spu_id",spuId);
            queryWrapper.eq("base_sale_attr_id",spuSaleAttr.getBaseSaleAttrId());
            List<SpuSaleAttrValue> spuSaleAttrValues = spuSaleAttrValueMapper.selectList(queryWrapper);
            spuSaleAttr.setSpuSaleAttrValueList(spuSaleAttrValues);
        }
        return spuSaleAttrList;
    }

}

package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SkuAttrValue;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SkuSaleAttrValue;
import com.atguigu.gmall.product.mapper.SkuAttrValueMapper;
import com.atguigu.gmall.product.mapper.SkuImageMapper;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import com.atguigu.gmall.product.mapper.SkuSaleAttrValueMapper;
import com.atguigu.gmall.product.service.SkuApiService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SkuApiServiceImpl implements SkuApiService {
    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //添加skuInfo
        skuInfoMapper.insert(skuInfo);
        //添加skuImage
        Long sku_id = skuInfo.getId();
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        if (null!=skuImageList){
            for (SkuImage skuImage : skuImageList) {
                skuImage.setSkuId(sku_id);
                skuImageMapper.insert(skuImage);
        }}
        //添加skuAttrValue
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if (null!=skuAttrValueList) {
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(sku_id);
                skuAttrValueMapper.insert(skuAttrValue);
            }
        }
        //添加skuSaleAttrValue
        Long spu_id = skuInfo.getSpuId();
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if (null!=skuSaleAttrValueList){
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                skuSaleAttrValue.setSpuId(spu_id);
                skuSaleAttrValue.setSkuId(sku_id);
                skuSaleAttrValueMapper.insert(skuSaleAttrValue);
            }

        }
    }

    @Override
    public BigDecimal getPrice(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);

        return skuInfo.getPrice();
    }

    @Override
    public SkuInfo getSkuInfo(Long skuId) {
         SkuInfo skuInfo=skuInfoMapper.selectById(skuId);
        QueryWrapper<SkuImage> wrapper = new QueryWrapper<>();
        wrapper.eq("sku_id",skuId);
        List<SkuImage> skuImageList = skuImageMapper.selectList(wrapper);
        skuInfo.setSkuImageList(skuImageList);
        return skuInfo;
    }
}

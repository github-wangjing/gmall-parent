package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import com.atguigu.gmall.product.service.BaseSkuManageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseSkuManageServiceImpl implements BaseSkuManageService {
    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Override
    public IPage<SkuInfo> getSkuList(Page<SkuInfo> spuInfoPage) {

        IPage<SkuInfo> skuInfoIPage = skuInfoMapper.selectPage(spuInfoPage,null);
        return skuInfoIPage;

    }

    @Override
    public void onSale(Long skuInfoId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuInfoId);
        skuInfo.setIsSale(1);
        skuInfoMapper.updateById(skuInfo);
        //todo 需要进行缓存或者nosql处理
    }

    @Override
    public void cancelSale(Long skuInfoId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuInfoId);
        skuInfo.setIsSale(0);
        skuInfoMapper.updateById(skuInfo);
        //todo 需要进行缓存或者nosql处理

    }
}

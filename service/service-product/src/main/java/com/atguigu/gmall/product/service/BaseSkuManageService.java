package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface BaseSkuManageService {
    IPage<SkuInfo> getSkuList(Page<SkuInfo> spuInfoPage);

    void onSale(Long skuInfoId);

    void cancelSale(Long skuInfoId);
}

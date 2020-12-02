package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SkuInfo;

import java.math.BigDecimal;

public interface SkuApiService {
    void saveSkuInfo(SkuInfo skuInfo);

    BigDecimal getPrice(Long skuId);

    SkuInfo getSkuInfo(Long skuId);
}

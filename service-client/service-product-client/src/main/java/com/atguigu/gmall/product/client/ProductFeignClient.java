package com.atguigu.gmall.product.client;

import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "service-product")
public interface ProductFeignClient {

    @RequestMapping("api/product/getPrice/{skuId}")
    BigDecimal getPrice(@PathVariable("skuId") Long skuId);

    @RequestMapping("api/product/getSkuInfo/{skuId}")
    SkuInfo getSkuInfo(@PathVariable("skuId")Long skuId);

    @RequestMapping("api/product/getSkuSaleAttrValue/{spuId}")
    List<SpuSaleAttr> getSkuSaleAttrValue(@PathVariable("spuId")Long spuId);

    @RequestMapping("api/product/getCategoryViewByCategory3Id/{category3_id}")
    BaseCategoryView getCategoryViewByCategory3Id(@PathVariable("category3_id")Long category3Id);
}

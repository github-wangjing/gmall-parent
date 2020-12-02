package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.item.service.ItemService;
import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ProductFeignClient productFeignClient;
    @Override
    public Map<String, Object> getItem(Long skuId) {
        Map<String, Object> map = new HashMap<>();
        //查询商品价格
        BigDecimal bigDecimal=productFeignClient.getPrice(skuId);
        //查询商品sku
       SkuInfo skuInfo= productFeignClient.getSkuInfo(skuId);
        //查询商品销售属性
       List<SpuSaleAttr> spuSaleAttrList =productFeignClient.getSkuSaleAttrValue(skuInfo.getSpuId());
        //分类导航信息查询
        BaseCategoryView baseCategoryView= productFeignClient.getCategoryViewByCategory3Id(skuInfo.getCategory3Id());
        map.put("price",bigDecimal);
        map.put("skuInfo",skuInfo);
        map.put("spuSaleAttrList",spuSaleAttrList);
        map.put("categoryView",baseCategoryView);
         return map;
    }
}

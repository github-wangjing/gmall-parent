package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.CategoryService;
import com.atguigu.gmall.product.service.SkuApiService;
import com.atguigu.gmall.product.service.SpuApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductApiController {
    @Autowired
    SkuApiService skuApiService;
    @Autowired
    SpuApiService spuApiService;

    @RequestMapping("getPrice/{skuId}")
    BigDecimal getPrice(@PathVariable("skuId")Long skuId){
        BigDecimal bigDecimal = new BigDecimal("0");
        bigDecimal= skuApiService.getPrice(skuId);

        return bigDecimal;
    }

    @RequestMapping("getSkuInfo/{skuId}")
    SkuInfo getSkuInfo(@PathVariable("skuId")Long skuId){

        SkuInfo skuInfo=skuApiService.getSkuInfo(skuId);

        return skuInfo;
    }

    @RequestMapping("getSkuSaleAttrValue/{spuId}")
    List<SpuSaleAttr> getSkuSaleAttrValue(@PathVariable("spuId")Long spuId){

        List<SpuSaleAttr> spuSaleAttrList = spuApiService.spuSaleAttrList(spuId);
        return spuSaleAttrList;
    }

    @Autowired
    CategoryService categoryService;
    @RequestMapping("getCategoryViewByCategory3Id/{category3_id}")
   BaseCategoryView getCategoryViewByCategory3Id(@PathVariable("category3_id")Long category3Id){
      BaseCategoryView baseCategoryView= categoryService.getCategoryViewByCategory3Id(category3Id);
        return baseCategoryView;
    }
}

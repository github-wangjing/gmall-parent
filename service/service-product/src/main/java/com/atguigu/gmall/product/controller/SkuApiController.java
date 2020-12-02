package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.service.SkuApiService;
import com.atguigu.gmall.product.service.SpuApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class SkuApiController {
    @Autowired
    SpuApiService spuApiService;
    @Autowired
    SkuApiService skuApiService;


    @RequestMapping("spuImageList/{spuId}")
    public Result spuImageList(@PathVariable("spuId") Long spuId){
       List<SpuImage> spuImageList= spuApiService.spuImageList(spuId);
        return Result.ok(spuImageList);
    }
    @RequestMapping("spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable("spuId") Long spuId){
        List<SpuSaleAttr> spuSaleAttrList= spuApiService.spuSaleAttrList(spuId);
        return Result.ok(spuSaleAttrList);
    }


    @RequestMapping("saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){

        skuApiService.saveSkuInfo(skuInfo);
        return Result.ok();
    }
}

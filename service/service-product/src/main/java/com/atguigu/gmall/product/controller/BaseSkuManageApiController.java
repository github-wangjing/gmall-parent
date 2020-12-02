package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.BaseSkuManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class BaseSkuManageApiController {
    @Autowired
    private BaseSkuManageService baseSkuManageService;

    //sku管理 页面分页查询
    @RequestMapping("list/{pageNo}/{size}")
    public Result getSkuList(@PathVariable("pageNo")Long pageNo,
                             @PathVariable("size") Long size){
        Page<SkuInfo> spuInfoPage = new Page<>(pageNo,size);
        IPage<SkuInfo> skuInfoIPage= baseSkuManageService.getSkuList(spuInfoPage);
        return Result.ok(skuInfoIPage);
    }
//    /admin/product/cancelSale/13
//    /admin/product/onSale/14S

    //上架
    @RequestMapping("onSale/{skuInfoId}")
    public Result onSale(@PathVariable("skuInfoId")Long skuInfoId){
        baseSkuManageService.onSale(skuInfoId);
        return Result.ok();
    }
    //下架
    @RequestMapping("cancelSale/{skuInfoId}")
    public Result cancelSale(@PathVariable("skuInfoId")Long skuInfoId){
        baseSkuManageService.cancelSale(skuInfoId);
        return Result.ok();
    }


}

package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.SpuApiService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class SpuApiController {

    @Autowired
    private SpuApiService spuApiService;

    //spu主页面内容展示
    @RequestMapping("{pageNo}/{size}")
    public Result getSpuList(@PathVariable("pageNo")Long pageNo,
                             @PathVariable("size") Long size, Long category3Id){
        Page<SpuInfo> spuInfoPage = new Page<>(pageNo,size);
        IPage<SpuInfo> spuInfoIPage= spuApiService.getSpuList(spuInfoPage,category3Id);
        return Result.ok(spuInfoIPage);
    }

    //添加页面的sku属性显示
    @RequestMapping("baseSaleAttrList")
    public Result baseSaleAttrList(){
        List<BaseSaleAttr> baseSaleAttrList = spuApiService.baseSaleAttrList();
        return Result.ok(baseSaleAttrList);
    }

    //添加spu
    @RequestMapping("saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        spuApiService.saveSpuInfo(spuInfo);
        return Result.ok();
    }
}

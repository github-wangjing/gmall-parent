package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.AttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("admin/product")
public class AttrInfoApiController {
    @Autowired
    private AttrInfoService attrInfoService;

    @RequestMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable("category3Id") Long category3Id){
        List<BaseAttrInfo> baseAttrInfoList =attrInfoService.attrInfoList(category3Id);
        return  Result.ok(baseAttrInfoList);
    }
   //添加属性
    @RequestMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {
        attrInfoService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }
    //修改的数据回显  localhost:8080/admin/product/getAttrValueList/6
    @RequestMapping("getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable("attrId")Long attrId ) {
        List<BaseAttrValue> baseAttrValueList = attrInfoService.getAttrValueList(attrId);
        return Result.ok(baseAttrValueList);
    }
}

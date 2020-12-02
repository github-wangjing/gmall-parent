package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class TrademarkApiController {
   @Autowired
   private TrademarkService trademarkService;

    //添加页面的spu品牌属性显示
    @RequestMapping("baseTrademark/getTrademarkList")
    public Result getTrademarkList(){
        List<BaseTrademark> baseSaleAttrList = trademarkService.getTrademarkList();
        return Result.ok(baseSaleAttrList);
    }
}

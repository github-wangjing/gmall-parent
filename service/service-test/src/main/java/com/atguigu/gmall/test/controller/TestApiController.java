package com.atguigu.gmall.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/test")
@RestController
public class TestApiController {
    @RequestMapping("test")
    public String test(){
        return "hello,world";
    }
}

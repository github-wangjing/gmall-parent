package com.atguigu.gmall.all.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class itemController {
    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping("test")
    public String test(Model model) {
        model.addAttribute("hello","thymeleaf没有学过啊");
        return "test";
    }
}

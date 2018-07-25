package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo1")
public class SampleController {

    @Autowired
    UserService userService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","wangfei");
        return "hello";
    }

    //找不到hello2.html 页面
    @RequestMapping("/thymeleaf2")
    public String thymeleaf2(Model model){
        model.addAttribute("name","wangfei");
        return "hello2";
    }


    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user=userService.getById(1);
        return Result.success(user);
    }

    //插入2条数据，加@Transactional, 二条插入失效，数据回滚
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        boolean result = userService.tx();
        return Result.success(result);
    }

    //插入2条数据，不加@Transactional,第一条插入成功，第二条失效
    @RequestMapping("/db/notx")
    @ResponseBody
    public Result<Boolean> dbnoTx(){
        boolean result = userService.notx();
        return Result.success(result);
    }
}
package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.rabbitmq.MQSender;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    /*****************************************************
     * 测试四种交换机
     **************************************************************/
    /**
    @RequestMapping("/mq")
    @ResponseBody
    public Result<String> mq(){
        sender.send("hello ,mq tester.");
        return Result.success("Hello world!");
    }

    @RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic(){
        sender.sendTopic("hello ,mq tester toppic.");
        return Result.success("Hello world!");
    }

    @RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout(){
        sender.sendFanout("hello ,mq tester fanout.");
        return Result.success("Hello world!");
    }

    @RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> header(){
        sender.sendHeader("hello ,mq tester header.");
        return Result.success("Hello world!");
    }
    */


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

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.getById,""+1,User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<User> redisSet(){
        User user = new User();
        user.setId(1);
        user.setName("1111");
        Boolean result = redisService.set(UserKey.getById,""+user.getId(),user);
        User getUser =  redisService.get(UserKey.getById,""+user.getId(),User.class);
        return Result.success(getUser);
    }

    @RequestMapping("/redis/exists")
    @ResponseBody
    public Result<Boolean> redisExists(){
        Boolean result= redisService.exists(UserKey.getById,""+1);
        return Result.success(result);
    }

    @RequestMapping("/redis/incr")
    @ResponseBody
    public Result<Long> redisIncr(){
        redisService.set(UserKey.getById,"Seq",100);
        Long result= redisService.incr(UserKey.getById,"Seq");
        return Result.success(result);
    }

    @RequestMapping("/redis/decr")
    @ResponseBody
    public Result<Long> redisDecr(){
        Long result= redisService.decr(UserKey.getById,"Seq");
        return Result.success(result);
    }
}

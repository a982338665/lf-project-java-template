package pers.li.aseckill.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.li.aseckill.entity.Test;
import pers.li.aseckill.redis.RedisService;
import pers.li.aseckill.redis.service.TestKey;
import pers.li.aseckill.result.CodeMsg;
import pers.li.aseckill.result.Result;
import pers.li.aseckill.service.TestService;

import java.util.Map;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@Controller
@RequestMapping("/demo")
public class SampleController
{

    @Autowired
    TestService testService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","luofeng--22222222222.111111111111111111");
        return "hello";
    }
    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error(){
        return Result.error(CodeMsg.SESSION_ERROR);
    }
    @RequestMapping("/test")
    @ResponseBody
    public Result<Test> test(@RequestParam("id") Integer id){
        Test byId = testService.getById(id);
        return Result.success(byId);
    }
    @RequestMapping("/test2")
    @ResponseBody
    public Result<Map> test2(@RequestParam("id") Integer id){
        Map byId = testService.getByIdMAP(id);
        return Result.success(byId);
    }
    @RequestMapping("/insert")
    @ResponseBody
    public boolean insert(){
        boolean bool = testService.insert();
        return bool;
    }
    @RequestMapping("/redis/get")
    @ResponseBody
    public String redisget(){
        boolean bool = redisService.set(TestKey.TestById, "0", "123");
        System.err.println("---------------->"+bool);
        Test tt=new Test();
        tt.setId(1);
        tt.setName("lishengbo");
        boolean bool2 = redisService.set(TestKey.TestById, "1", tt);
        String s = redisService.get(TestKey.TestById, "0", String.class);
        Test s1 = redisService.get(TestKey.TestById, "1", Test.class);
        System.err.println("---------------->"+s);
        return bool+"|"+s+"|"+bool2+"|"+s1.toString();
    }


}
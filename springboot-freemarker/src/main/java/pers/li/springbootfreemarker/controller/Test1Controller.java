package pers.li.springbootfreemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pers.li.springbootfreemarker.entity.User;

import java.util.*;

@Controller
public class Test1Controller {

    @RequestMapping("/test1")
    public String test1(ModelMap modelMap) {

        modelMap.addAttribute("intVar",100);
        modelMap.addAttribute("longVar",1000L);
        modelMap.addAttribute("StringVar","hello,world");
        modelMap.addAttribute("DoubleVar",2.15d);
        modelMap.addAttribute("boolVar",true);
        modelMap.addAttribute("boolVarfalse",false);
        modelMap.addAttribute("currentTime",new Date());
        modelMap.addAttribute("nullVar",null);

        User user = new User();
        user.setId("12");
        user.setName("姓名");

        modelMap.addAttribute("userInfo",user);

        User user2 = new User();
        user2.setId("12");
        user2.setName("<font color='red'>富文本测试</font>");
        modelMap.addAttribute("htmlUserInfo",user2);

        List<String> list=new ArrayList<>();
        list.add("list测试1");
        list.add("list测试2");
        list.add("list测试3");
        modelMap.addAttribute("ListStringInfo",list);

        List<User> users=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User usera = new User();
            usera.setId(i+"");
            usera.setName(i+"姓名");
            users.add(usera);
            usera=null;
        }
        modelMap.addAttribute("ListUserInfo",users);

        Map<String,String> map=new HashMap<>();
        map.put("map-key-1","map-value-1");
        map.put("map-key-2","map-value-2");
        map.put("map-key-3","map-value-3");
        modelMap.addAttribute("MapInfo",map);

        return "index";
    }


}
package pers.li.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.li.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class Test1Controller {

    @RequestMapping("/test1")
    public String test1(ModelMap modelMap) {
        modelMap.addAttribute("guest", "hello,freemarker");
        modelMap.addAttribute("list", getList());
        modelMap.addAttribute("timevar", new Date());
        return "test1";
    }

    public List<User> getList(){
        List<User> list = new ArrayList<User>();
        for (int i = 0; i <10 ; i++) {
            User user = new User();
            user.setId(i+"");
            user.setName(i+"+name");
            list.add(user);
            user=null;
        }
        return  list;
    }
}
package pers.li.springbootfreemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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
     return "index";
    }


}
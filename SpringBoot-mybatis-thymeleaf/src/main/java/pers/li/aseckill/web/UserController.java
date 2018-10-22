package pers.li.aseckill.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.service.SGoodService;
import pers.li.aseckill.service.SUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 * 测试优化瓶颈：SUser user结果封装测试
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    private static Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    SUserService sUserService;
    @Autowired
    SGoodService sGoodService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @RequestMapping("/info")
    @ResponseBody
    public String index(Model model,SUser user){
        return user.toString();
    }
}
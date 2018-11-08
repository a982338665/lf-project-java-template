package pers.li.aseckill.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.li.aseckill.result.Result;
import pers.li.aseckill.service.SUserService;
import pers.li.aseckill.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@Controller
@RequestMapping("/login")
public class LoginController
{
    private static Logger log= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SUserService sUserService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @RequestMapping("/index")
    public String index(){
        return "login";
    }
    @RequestMapping("/login")
    @ResponseBody
    public Result<String> login(@Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //参数校验
//        String mobile = loginVo.getMobile();
//        String password = loginVo.getPassword();
        //传统校验参数-----------------------------------
//        if(StringUtils.isEmpty(mobile)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if(StringUtils.isEmpty(password)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
//        CodeMsg codeMsg=sUserService.login(loginVo);
//        if(codeMsg.getCode()==0){
//            return Result.success(true);
//        }else{
//            return Result.error(codeMsg);
//        }
        //全局异常处理优化，参数检验异常直接抛出
        return Result.success(sUserService.login(response,loginVo));
    }



}
package pers.li.aseckill.web;


import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.result.Result;
import pers.li.aseckill.service.SGoodService;
import pers.li.aseckill.service.SUserService;
import pers.li.aseckill.vo.LoginVo;
import pers.li.aseckill.vo.SGoodsVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/9 16:02
 */
@Controller
@RequestMapping("/goods")
public class GoodsController
{
    private static Logger log= LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    SUserService sUserService;
    @Autowired
    SGoodService sGoodService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    /**
     * 可以通过cookie传递参数，也可以通过参数直接传递
     *  --优化注释：组装接收参数SUser替换参数  HttpServletResponse response，
     *   @CookieValue(value = SUserService.TOKEN_COOKIE,required = false) String cookieToken,
     *   @RequestParam(value = SUserService.TOKEN_COOKIE,required = false)String paramToken
     *   @return 返回值自动封装在SUser中
     */
    @RequestMapping("/index_list")
    public String index(Model model,SUser user){
        List<SGoodsVo> sGoodsVos = sGoodService.listSGoodsVo();
        model.addAttribute("user",user);
        model.addAttribute("goodsList",sGoodsVos);
        return "goods_list";
    }
    @RequestMapping("/to_detail/{goodsId}")
    public String toDetail(Model model,
                           SUser user,
                           @PathVariable("goodsId") long goodsId){
        model.addAttribute("user",user);

        SGoodsVo goods = sGoodService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);
        long startAt = goods.getStartTime().getTime();
        long endAt = goods.getEndTime().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        //秒杀还没开始，倒计时
        if(now < startAt ) {
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        //秒杀已经结束
        }else  if(now > endAt){
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }




}
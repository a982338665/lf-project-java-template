package pers.li.aseckill.web;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.redis.RedisService;
import pers.li.aseckill.redis.service.GoodsKey;
import pers.li.aseckill.service.SGoodService;
import pers.li.aseckill.service.SUserService;
import pers.li.aseckill.vo.SGoodsVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/15
 * 页面缓存+url缓存+对象缓存后的controller
 *
 */
@Controller
@RequestMapping("/htmlgoods")
@SuppressWarnings("all")
public class HtmlGoodsController
{
    private static Logger log= LoggerFactory.getLogger(HtmlGoodsController.class);

    @Autowired
    SUserService sUserService;
    @Autowired
    SGoodService sGoodService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    RedisService redisService;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 优化：--页面缓存
     *  --1.注入： ThymeleafViewResolver thymeleafViewResolver;RedisService redisService;ApplicationContext applicationContext;
     *  --2.请求mapping添加 produces = "text/html"
     *  --3. --取缓存  缓存有效期60s，防止数据更新不及时
             --手动渲染模板
             --结果输出
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(path = "/index_list", produces = "text/html")
    @ResponseBody
    public String index(Model model,SUser user){
        //取缓存
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if(!StringUtils.isEmpty(html)){
            return html;
        }
        List<SGoodsVo> sGoodsVos = sGoodService.listSGoodsVo();
        model.addAttribute("goodsList",sGoodsVos);
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        return html;
    }

    /**
     * 由于每个goodsId都可能不同，所以粒度更细，称为 url缓存
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(path = "/to_detail/{goodsId}",produces="text/html")
    public String toDetail(Model model,
                           SUser user,
                           @PathVariable("goodsId") long goodsId){
        model.addAttribute("user",user);
        //取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }
        //手动渲染
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
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
        }
        return html;
//        return "goods_detail";
    }




}
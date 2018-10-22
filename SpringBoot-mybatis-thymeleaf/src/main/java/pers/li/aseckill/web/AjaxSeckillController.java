package pers.li.aseckill.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.li.aseckill.entity.SOrderInfo;
import pers.li.aseckill.entity.SOrderSeckill;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.result.CodeMsg;
import pers.li.aseckill.result.Result;
import pers.li.aseckill.service.SGoodService;
import pers.li.aseckill.service.SOrderService;
import pers.li.aseckill.service.SeckillService;
import pers.li.aseckill.vo.SGoodsVo;

/**
 * 秒杀静态化：数据传输使用json，亲后端交互使用ajax
 */
@Controller
@RequestMapping("/seckill")
public class AjaxSeckillController {

	@Autowired
	SGoodService sGoodService;
	@Autowired
	SOrderService sOrderService;
	@Autowired
	SeckillService seckillService;
    /**
     * QPS:1306
     * 5000 * 10
     * */
    /**
     *  GET POST有什么区别？
     * */
    @RequestMapping(value="/seckill", method= RequestMethod.POST)
    @ResponseBody
    public Result<SOrderInfo> miaosha(Model model,SUser user,
                                     @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //判断库存//10个商品，req1 req2
        SGoodsVo goods = sGoodService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getGoodsStock();
        if(stock <= 0) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否已经秒杀到了
        SOrderSeckill orderSeckill=sOrderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(orderSeckill != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //减库存 下订单 写入秒杀订单
        SOrderInfo orderInfo = seckillService.seckill(user, goods);
        return Result.success(orderInfo);
    }
}

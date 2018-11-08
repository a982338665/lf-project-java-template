package pers.li.aseckill.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.li.aseckill.entity.SOrderInfo;
import pers.li.aseckill.entity.SOrderSeckill;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.result.CodeMsg;
import pers.li.aseckill.service.SGoodService;
import pers.li.aseckill.service.SOrderService;
import pers.li.aseckill.service.SeckillService;
import pers.li.aseckill.vo.SGoodsVo;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

	@Autowired
	SGoodService sGoodService;
	@Autowired
	SOrderService sOrderService;
	@Autowired
	SeckillService seckillService;

    @RequestMapping("/do_seckill")
    public String list(Model model,SUser user,
    		@RequestParam("goodsId")long goodsId) {
    	if(user==null){
			return "login";
		}
		model.addAttribute("user",user);

		SGoodsVo goods = sGoodService.getGoodsVoByGoodsId(goodsId);
		//判断活动是否开始
		long time =System.currentTimeMillis();
		if(time<goods.getStartTime().getTime() || time>goods.getEndTime().getTime()){
			model.addAttribute("errmsg", CodeMsg.NO_PASS_SECKILL.getMsg());
			model.addAttribute("errcode", CodeMsg.NO_PASS_SECKILL.getCode());
			return "seckill_fail";
		}
		//判断库存
		int stock = goods.getSeckillStock();
		if(stock <= 0) {
			model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
			model.addAttribute("errcode", CodeMsg.MIAO_SHA_OVER.getCode());
			return "seckill_fail";
		}
		//判断是否已经秒杀过，限购一个
		SOrderSeckill orderSeckill=sOrderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
		if(orderSeckill != null) {
			model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
			model.addAttribute("errcode", CodeMsg.REPEATE_MIAOSHA.getCode());

			return "seckill_fail";
		}
		//减库存 下订单 写入秒杀订单
		SOrderInfo orderInfo = seckillService.seckill(user, goods);
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("goods", goods);
		return "order_detail";
    }
}

package pers.li.aseckill.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.li.aseckill.dao.SGoodDao;
import pers.li.aseckill.dao.SOrderDao;
import pers.li.aseckill.entity.SOrderInfo;
import pers.li.aseckill.entity.SOrderSeckill;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.redis.RedisService;
import pers.li.aseckill.result.OrderEnum;
import pers.li.aseckill.vo.SGoodsVo;

import java.util.Date;
import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:31
 */
@Service
public class SOrderService {

    private static Logger log= LoggerFactory.getLogger(SOrderService.class);

    @Autowired
    SOrderDao sOrderDao;

    public SOrderSeckill getMiaoshaOrderByUserIdGoodsId(Long id, long goodsId) {
        return sOrderDao.getMiaoshaOrderByUserIdGoodsId(id,goodsId);
    }

   @Transactional(rollbackFor = Exception.class )
    public SOrderInfo createOrder(SUser user, SGoodsVo goods) {
        //s_order_info
        SOrderInfo sOrderInfo=new SOrderInfo();
        sOrderInfo.setCreateTime(new Date());
        sOrderInfo.setDeliveryAddrId(0L);
        sOrderInfo.setGoodsCount(1);
        sOrderInfo.setGoodsId(goods.getId());
        sOrderInfo.setGoodsName(goods.getGoodsName());
        sOrderInfo.setGoodsPrice(goods.getGoodsPrice());
        sOrderInfo.setSeckillPrice(goods.getSeckillPrice());
        sOrderInfo.setOrderChannel(1);
        sOrderInfo.setStatus(OrderEnum.NO_PAY.getStatus());
        sOrderInfo.setUserId(user.getId());
        long orderId=sOrderDao.insertOrderInfo(sOrderInfo);
        //s_order_seckill
        SOrderSeckill orderSeckill=new SOrderSeckill();
        orderSeckill.setGoodsId(goods.getId());
        orderSeckill.setOrderId(orderId);
        orderSeckill.setUserId(user.getId());
        sOrderDao.insertSOrderSeckill(orderSeckill);
        return sOrderInfo;
    }
}

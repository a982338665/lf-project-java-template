package pers.li.aseckill.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import pers.li.aseckill.dao.SGoodDao;
import pers.li.aseckill.entity.SOrderInfo;
import pers.li.aseckill.entity.SOrderSeckill;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.redis.RedisService;
import pers.li.aseckill.vo.SGoodsVo;

import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:31
 */
@Service
public class SeckillService {

    private static Logger log= LoggerFactory.getLogger(SeckillService.class);

    @Autowired
    SGoodService sGoodService;
    @Autowired
    SOrderService sOrderService;


    /**
     * 自动事务回滚
     * @param user
     * @param goods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SOrderInfo seckill(SUser user, SGoodsVo goods) {
        //减库存，下订单，写入秒杀订单---最好将逻辑写在相对应的service中，注入service
        sGoodService.reduceStock(goods);
        //s_order_info s_order_seckill
        return sOrderService.createOrder(user,goods);

    }
    /**
     *   手动回滚事务--if测试
     *   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     * @param user
     * @param goods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SOrderInfo seckill2(SUser user, SGoodsVo goods) {
        //减库存，下订单，写入秒杀订单---最好将逻辑写在相对应的service中，注入service
        SOrderInfo order =null;
        sGoodService.reduceStock(goods);
        order=sOrderService.createOrder(user, goods);
        //s_order_info s_order_seckill
        int x=0;
        if(x==0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("if-测试事务回滚：手动回滚");
        }
        return order;

    }
    /**
     *   手动回滚事务--try-catch测试
     *   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     * @param user
     * @param goods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SOrderInfo seckill3(SUser user, SGoodsVo goods) {
        SOrderInfo order =null;
        try{
            //减库存，下订单，写入秒杀订单---最好将逻辑写在相对应的service中，注入service
            sGoodService.reduceStock(goods);
            order=sOrderService.createOrder(user, goods);
            int x=1/0;
            //s_order_info s_order_seckill
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("try-catch-测试事务回滚：手动回滚");
        }
        return order;

    }
}

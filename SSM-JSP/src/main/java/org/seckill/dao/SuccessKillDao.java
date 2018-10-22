package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilledBean;

/**
 * create by lishengbo on 2017-12-28 10:57
 */
public interface SuccessKillDao {
    /**
     * 插入购买明细，可过滤重复
     * @param userid
     * @param userphone
     * @return
     */
    int insertSuccesskilled(@Param("userid")long userid ,@Param("userphone") long userphone);

    /**
     * 根据id查询秒杀成功明细表，并携带秒杀商品的实体对象
     * @param seckillId
     * @return
     */
    SuccessKilledBean queryByIdWhitSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);

}

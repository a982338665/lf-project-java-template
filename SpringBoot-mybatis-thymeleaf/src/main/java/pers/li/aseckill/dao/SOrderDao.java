package pers.li.aseckill.dao;

import org.apache.ibatis.annotations.*;
import pers.li.aseckill.entity.SOrderInfo;
import pers.li.aseckill.entity.SOrderSeckill;
import pers.li.aseckill.vo.SGoodsVo;

import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:26
 */
@Mapper
public interface SOrderDao {

    @Select("select * from s_order_seckill where user_id=#{userId} and goods_id=#{goodsId}")
    SOrderSeckill getMiaoshaOrderByUserIdGoodsId(@Param("userId")Long id, @Param("goodsId")long goodsId);

    @Insert("insert into s_order_info " +
            "(user_id,goods_id,delivery_add_id,goods_name,goods_count,goods_price," +
            "order_channel,seckill_price,`status`,create_time)values("+
            "#{userId},#{goodsId},#{deliveryAddrId},#{goodsName},#{goodsCount},#{goodsPrice}," +
            "#{orderChannel},#{seckillPrice},#{status},#{createTime}" +
            " )")
    /** keyColumn--指表的某一列
     *  keyProperty--列对应的entity中的属性名
     *  resultType--返回结果类型
     *  before=false --在插入之后查询
     *  statement="select last_insert_id()" --查询最后一次增加的id
     * */
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    long insertOrderInfo(SOrderInfo sOrderInfo);

    @Insert("insert into s_order_seckill (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    void insertSOrderSeckill(SOrderSeckill orderSeckill);
}

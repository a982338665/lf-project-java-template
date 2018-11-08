package pers.li.aseckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.li.aseckill.entity.SGoodsSeckill;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.vo.SGoodsVo;

import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:26
 */
@Mapper
public interface SGoodDao {

    @Select("SELECT g.*,mg.seckill_price,mg.seckill_stock,mg.start_time,mg.end_time from s_goods_seckill mg" +
            " LEFT JOIN s_goods g on mg.goods_id=g.id")
    List<SGoodsVo> getSGoodsVo();

    @Select("SELECT g.*,mg.seckill_price,mg.seckill_stock,mg.start_time,mg.end_time from s_goods_seckill mg" +
            " LEFT JOIN s_goods g on mg.goods_id=g.id where mg.goods_id=#{id}")
    SGoodsVo getSeckillGoodbyId(@Param("id") long goodsId);

    @Update("UPDATE s_goods_seckill set seckill_stock=seckill_stock-1 where goods_id=#{goodsId}")
    void reduceStock(SGoodsSeckill goods);
}

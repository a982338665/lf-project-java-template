package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SeckillBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * create by lishengbo on 2017-12-28 10:51
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 影响几条数据
     */
    int reduceNumber (@Param("seckillId")long seckillId,@Param("killTime") Date killTime);
    /**
     * 减库存
     * @return 影响几条数据
     */
    int reduceNumberMap (Map<String,String> map);
    /**
     * 根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    SeckillBean queryById(long seckillId);
    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<SeckillBean> queryAll(@Param("offset")int offset,@Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     * @param param
     */
    void killByProceduce(Map<String,Object> param);


}

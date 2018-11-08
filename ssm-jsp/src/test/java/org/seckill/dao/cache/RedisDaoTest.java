package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.SeckillBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * create by lishengbo on 2018-01-04 16:58
 * redis 单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RedisDaoTest {

    private long id=1001;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;

    /**'
     * 测试测方法前要开启redis服务器
     * @throws Exception
     */
    @Test
    public void getSeckill() throws Exception {
        SeckillBean seckill =   redisDao.getSeckill(id);
        if (seckill==null){
            seckill=seckillDao.queryById(id);
            if(seckill!=null){
                String result=redisDao.putSeckill(seckill);
                System.out.println(result);
                seckill=redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        }

    }

}
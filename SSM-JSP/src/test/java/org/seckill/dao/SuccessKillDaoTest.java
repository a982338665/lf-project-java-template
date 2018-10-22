package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilledBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * create by lishengbo on 2017-12-29 17:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKillDaoTest {

    /**
     *注入Dao实现类依赖
     */
    @Resource
    private SuccessKillDao successKillDao;

    /**
     * 第一次新增成功
     * 第二次失败但不报错（insert ignore ...,但是返回1）
     * @throws Exception
     */
    @Test
    public void insertSuccesskilled() throws Exception {
        int i = successKillDao.insertSuccesskilled(1000L, 13884500949L);
        System.out.println(i);
    }

    @Test
    public void queryByIdWhitSeckill() throws Exception {
        SuccessKilledBean successKilledBean = successKillDao.queryByIdWhitSeckill(1000L, 13884500939L);
        System.out.println(successKilledBean.toString());

    }

}
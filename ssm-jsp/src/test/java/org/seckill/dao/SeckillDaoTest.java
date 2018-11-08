package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SeckillBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.swing.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * create by lishengbo on 2017-12-28 14:56
 * 配置Spring junit整合，junit启动时要加载spring ioc容器
 * spring-test、junit依赖
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
    /**
     *注入Dao实现类依赖
     */
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() throws Exception {
        int i = seckillDao.reduceNumber(1000L, new Date());
        System.out.println(i);
    }
    @Test
    public void reduceNumberMap() throws Exception {
        Map<String,String> map=new HashMap<String, String>();
        map.put("seckillId","1000");
        map.put("killTime","2017-12-31 00:00:00");
        int i = seckillDao.reduceNumberMap(map);
        System.out.println(i);
    }


    @Test
    public void queryById() throws Exception {
        long id=1000L;
        SeckillBean seckillBean = seckillDao.queryById(id);
        System.out.println(seckillBean.toString());

    }

    /**
     *  org.apache.ibatis.binding.BindingException:(在进行数据绑定时，找不到参数offset)
     *  Parameter 'offset' not found. Available parameters are [1, 0, param1, param2]
     * @throws Exception
     * 异常原因：
     * java没有保存形参的记录：即
     * queryAll( int offset,int limit)--> queryAll( int arg0,int arg1)
     * 故无法找到offset参数，所以需要加注解@Param("offset")
     */
    @Test
    public void queryAll() throws Exception {
        List<SeckillBean> seckillBeans = seckillDao.queryAll(0, 100);
        System.out.println(seckillBeans.toString());
    }

}
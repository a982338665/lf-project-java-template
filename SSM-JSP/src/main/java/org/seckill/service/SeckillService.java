package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SeckillBean;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口;站在使用者角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型(return 类型/异常 )
 * create by lishengbo on 2018-01-02 10:12
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<SeckillBean> getSeckillList();

    /**
     * 查询单个秒杀纪录
     * @param seckillId
     * @return
     */
    SeckillBean getById(long seckillId);

    /**
     * 秒杀开启时输出接口地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckill(long seckillId,long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
    /**
     * 执行秒杀操作通过存储过程
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckillByProceduce(long seckillId,long userPhone,String md5);
}

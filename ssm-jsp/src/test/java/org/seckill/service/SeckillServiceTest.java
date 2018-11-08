package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SeckillBean;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * service 测试
 * create by lishengbo on 2018-01-02 15:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<SeckillBean> seckillList = seckillService.getSeckillList();
        logger.info("list={}",seckillList);
//         Closing non transactional SqlSession  非事务控制下
    }

    @Test
    public void getById() throws Exception {
        SeckillBean byId = seckillService.getById(1000);
        logger.info("seckillBean={}",byId);
    }

    /**
     * 测试代码完整逻辑
     * @throws Exception
     */
    @Test
    public void exportSeckillUrl() throws Exception {
//        exposer=Exposer{exposed=true, md5='8fd24a92caad5d334dbe58601e667496', seckillId=1001, now=0, start=0, end=0}
//        Exposer exposer = seckillService.exportSeckillUrl(1000);
        long id =1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        long phone=12346668585L;
        String md5=exposer.getMd5();
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            try {
                SeckillExecution seckillExecution =
                        seckillService.executeSeckill
                                (id, phone, md5);
                logger.info("result={}",seckillExecution);
            } catch (SeckillException e) {
                logger.error("exception={}",e.getMessage());
            }
        }else{
            logger.warn("exposer={}",exposer);
        }
    }
    /**
     * 测试代码完整逻辑
     * @throws Exception
     */
    @Test
    public void executeSeckilBYProceduce() throws Exception {
//        exposer=Exposer{exposed=true, md5='8fd24a92caad5d334dbe58601e667496', seckillId=1001, now=0, start=0, end=0}
//        Exposer exposer = seckillService.exportSeckillUrl(1000);
        long id =1001;
        long phone=13355555555L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        String md5=exposer.getMd5();
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            try {
                SeckillExecution seckillExecution =
                        seckillService.executeSeckillByProceduce
                                (id, phone, md5);
                logger.info("result={}",seckillExecution.getStateInfo());
            } catch (SeckillException e) {
                logger.error("exception={}",e.getMessage());
            }
        }else{
            logger.warn("exposer={}",exposer);
        }
    }

    @Test
    public void executeSeckill() throws Exception {
        try {
            SeckillExecution seckillExecution =
                    seckillService.executeSeckill
                            (1001, 13884500959L, "8fd24a92caad5d334dbe58601e667496");
            logger.info("result={}",seckillExecution);
        } catch (SeckillException e) {
            logger.error("exception={}",e.getMessage());
        }
    }

}
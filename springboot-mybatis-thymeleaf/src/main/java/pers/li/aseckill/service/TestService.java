package pers.li.aseckill.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.li.aseckill.dao.TestDao;
import pers.li.aseckill.entity.Test;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:31
 */
@Service
public class TestService {

    @Resource
    TestDao testDao;

    public Test getById(int x){
        return testDao.getTestById(x);
    }

    public Map getByIdMAP(Integer id) {
        return testDao.getByIdMAP(id);
    }

    /**事物测试*/
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(){
        try{
            Test a1=new Test(7,"lishengbo",(byte)0);
            Test a2=new Test(2,"lishengbo",(byte)0);
            testDao.insert(a1);
            testDao.insert(a2);
        }catch(Exception e){
            //try-catch后异常必须抛出，否则事物无效
            System.err.println("---------------------->"+e.getMessage()+"---------------------->");
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }
}

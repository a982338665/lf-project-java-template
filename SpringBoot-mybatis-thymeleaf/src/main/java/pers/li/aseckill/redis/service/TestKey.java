package pers.li.aseckill.redis.service;

import pers.li.aseckill.entity.Test;
import pers.li.aseckill.redis.BasePrefix;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 14:57
 * @decription: 业务实现类测试
 */
public class TestKey extends BasePrefix {

    private TestKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    /**
     * 前缀为id的key的定义
     */
    public static TestKey TestById=new TestKey(0,"id");
    /**
     * 前缀为name的key的定义
     */
    public static TestKey TestByName=new TestKey(0,"name");


   /* public static void main(String[] args) {
        System.out.println(TestKey.TestById.getPrefix());
        System.out.println(TestKey.TestById);
        System.out.println(TestKey.TestById.getExpireSeconds());
    }*/
}

package org.seckill.exception;

/**
 * 秒杀业务相关的异常
 * create by lishengbo on 2018-01-02 11:46
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}

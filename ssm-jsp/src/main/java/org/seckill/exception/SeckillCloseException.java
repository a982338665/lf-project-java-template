package org.seckill.exception;

/**
 * 秒杀关闭异常：
 *
 * create by lishengbo on 2018-01-02 11:43
 */
public class SeckillCloseException  extends SeckillException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}

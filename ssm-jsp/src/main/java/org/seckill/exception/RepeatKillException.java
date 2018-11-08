package org.seckill.exception;

/**
 * 重复秒杀异常(运行期异常)--Spring不会对非运行异常()进行事务回滚
 * create by lishengbo on 2018-01-02 11:40
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}

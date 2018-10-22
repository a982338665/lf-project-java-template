-- 秒杀执行存储过程
use seckill;
-- ;分隔符决定是否换行
DELIMITER $$ -- console ; 转换为$$
-- 定义存储过程
-- 参数： in 输入参数，out 输出参数
-- row_count() 函数用来返回上一条修改类型sql(delete/update/insert)的影响函数
-- row_count():0-未修改数据  >1表示修改行数 <0表示 sql错误或者未执行修改sql
CREATE PROCEDURE `seckill`.`excute_seckill`
  (in v_seckill_id BIGINT,
   in v_phone bigint,
   in v_kill_time timestamp,
   out r_result int
  )
  BEGIN
    DECLARE insert_count int DEFAULT 0;
    START TRANSACTION ;
    INSERT IGNORE into success_killed
    (seckill_id, user_phone,create_time,state)
      VALUES (v_seckill_id,v_phone,v_kill_time,0);
    SELECT  row_count() INTO insert_count;
    IF (insert_count=0) THEN
      ROLLBACK ;
      SET  r_result=-1; -- 代表重复秒杀
    ELSEIF (insert_count<0) THEN
      ROLLBACK ;
      SET  r_result=-2; -- 代表系统异常
    ELSE
      UPDATE seckill
        set number=number-1
      where seckill_id=v_seckill_id
      and end_time>v_kill_time
      and start_time<v_kill_time
      and number>0;
    SELECT  row_count() INTO insert_count;
      IF (insert_count=0) THEN
        ROLLBACK ;
        SET  r_result=0;  -- 代表秒杀结束
      ELSEIF (insert_count<0) THEN
        ROLLBACK ;
        SET  r_result=-2; -- 代表系统异常
      ELSE
        COMMIT ;
        SET r_result=1;
      END IF ;
    END IF ;
  END $$
-- 表示存储过程定义结束

-- 查看存储过程
show CREATE PROCEDURE excute_seckill\G;

 -- 清屏 system clear;

-- 测试执行
DELIMITER ;
   SET  @r_result=-3;
    -- SELECT * FROM seckill where seckill_id=1003;;
    -- 执行存储过程
   CALL excute_seckill(1003,18745124124,now(),@r_result);
    -- 获取结果
   SELECT @r_result;

-- 存储过程：一般银行在使用，企业不太用
-- 1.存储过程优化：优化事务行级锁持有的时间
-- 2.不要过度依赖存储过程
-- 3.简单的逻辑可以应用存储过程
-- 4.QPS：一个秒杀单6000左右的QPS，多个秒杀单不存在竞争关系
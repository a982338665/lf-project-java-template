package org.seckill.entity;

import org.seckill.entity.SuccessKilledBean22;

public interface SuccessKilledBean22Mapper {
    int deleteByPrimaryKey(Long seckillId);

    int insert(SuccessKilledBean22 record);

    int insertSelective(SuccessKilledBean22 record);

    SuccessKilledBean22 selectByPrimaryKey(Long seckillId);

    int updateByPrimaryKeySelective(SuccessKilledBean22 record);

    int updateByPrimaryKey(SuccessKilledBean22 record);
}
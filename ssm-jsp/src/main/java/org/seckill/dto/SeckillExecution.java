package org.seckill.dto;

import org.seckill.entity.SuccessKilledBean;
import org.seckill.enums.SeckillStateEnum;

/**
 * 封装秒杀执行后的结果
 * create by lishengbo on 2018-01-02 11:33
 */
public class SeckillExecution {
    /**
     * 秒杀id
     */
    private long seckillId;
    /**
     * 秒杀执行结果的状态
     */
    private int state;
    /**
     * 状态表示
     */
    private String stateInfo;
    /**
     * 秒杀成功对象
     */
    private SuccessKilledBean successKilledBean;

    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilledBean successKilledBean) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.successKilledBean = successKilledBean;
    }

    public SeckillExecution(long seckillId,SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilledBean getSuccessKilledBean() {
        return successKilledBean;
    }

    public void setSuccessKilledBean(SuccessKilledBean successKilledBean) {
        this.successKilledBean = successKilledBean;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilledBean=" + successKilledBean +
                '}';
    }
}

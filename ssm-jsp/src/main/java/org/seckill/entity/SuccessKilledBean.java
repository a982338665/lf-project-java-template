package org.seckill.entity;

import java.util.Date;

public class SuccessKilledBean {
    private Long seckillId;
    private Long userPhone;
    private Byte state;
    private Date createTime;

    //变通
    //多对一:一件商品有多个秒杀成功者
    private SeckillBean seckill;

    public SeckillBean getSeckillBean() {
        return seckill;
    }

    public void setSeckillBean(SeckillBean seckillBean) {
        this.seckill = seckillBean;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "SuccessKilledBean{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", seckillBean=" + seckill +
                '}';
    }
}
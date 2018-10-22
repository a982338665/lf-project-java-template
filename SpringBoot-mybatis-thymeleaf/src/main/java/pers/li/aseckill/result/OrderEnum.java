package pers.li.aseckill.result;

import lombok.Data;

/**
 * @author:luofeng
 * @createTime : 2018/10/12 14:20
 * 订单相关枚举：0-未支付 1-已支付 2-未发货 3-已发货 .已退款 6-已完成
 */

public enum OrderEnum {

    NO_PAY(0,"未支付"),
    YES_PAY(1,"已支付"),
    NO_OUT_GOODS(2,"未发货"),
    YES_OUT_GOODS(3,"已发货"),
    YES_OUT_PAY(4,"已退款"),
    FINISH_TREAD(5,"已完成")

    ;
    private int status;

    private String value;

    OrderEnum(int status, String value) {
        this.status = status;
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }

}

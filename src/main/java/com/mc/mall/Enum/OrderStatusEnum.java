package com.mc.mall.Enum;

import lombok.Getter;

/**
 * @author mc
 * @create 2021-03-30 21:39
 **/
@Getter
public enum OrderStatusEnum {
    CANCELED(0, "已取消"),
    NO_PAY(10, "未付款"),
    PAID(20, "已付款"),
    SHIPPED(40, "已发货"),
    TRADE_SUCCESS(50, "交易成功"),
    TRADE_CLOSE(60, "交易失败"),
    ;

    Integer code;
    String desc;

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

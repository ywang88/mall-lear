package com.mc.mall.Enum;

import lombok.Getter;

/**
 * @author mc
 * @create 2021-03-30 21:34
 **/
@Getter
public enum PaymentTypeEnum {
    PAY_ONLINE(1),
    ;
    Integer code;

    PaymentTypeEnum(Integer code) {
        this.code = code;
    }
}

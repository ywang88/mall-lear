package com.mc.mall.Enum;

import lombok.Getter;

/**
 * Author wangyi
 * Create 2020-11-12 22:41
 * ClassName RoleEnum
 * Version 1.0
 */
@Getter
public enum RoleEnum {
    ADMIN(0),
    CUSTOMER(1),
    ;
    Integer code;

    RoleEnum(Integer code) {
        this.code = code;
    }
}

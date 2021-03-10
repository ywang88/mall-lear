package com.mc.mall.Enum;/**
 * @author mc
 * @create 2020-12-10 22:32
 **/

import lombok.Getter;

/**
 * Author wangyi
 * Create 2020-12-10 22:32
 * ClassName ProductStatusEnum
 * Version 1.0
 */
@Getter
public enum ProductStatusEnum {
    ON_SALE(1, "在售"),
    OFF_SALE(2, "下架"),
    DELETE(3, "删除"),
    ;
    Integer code;
    String name;

    ProductStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}

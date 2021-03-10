package com.mc.mall.from;/**
 * @author mc
 * @create 2021-03-04 22:01
 **/

import lombok.Data;

/**
 * Author wangyi
 * Create 2021-03-04 22:01
 * ClassName CartUpdateFarm
 * Version 1.0
 */
@Data
public class CartUpdateFarm {
    //非必填
    private Integer quantity;
    private Boolean selected;
}

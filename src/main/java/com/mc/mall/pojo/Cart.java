package com.mc.mall.pojo;/**
 * @author mc
 * @create 2021-01-30 23:59
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author wangyi
 * Create 2021-01-30 23:59
 * ClassName Cart
 * Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    /*   商品id*/
    private Integer productId;
    /*   商品数量*/
    private Integer quantity;
    /*   商品是否选中*/
    private Boolean productSelected;
}

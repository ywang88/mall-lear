package com.mc.mall.vo;/**
 * @author mc
 * @create 2021-01-30 22:24
 **/

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 * Author wangyi
 * Create 2021-01-30 22:24
 * ClassName CartVo
 * Version 1.0
 */
@Data
public class CartVo {
    private List<CartProductVo> cartProductVoList;
    private Boolean selectAll;
    private BigDecimal cartTotalPrice;
    private Integer cartTotalQuantity;
}

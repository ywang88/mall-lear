package com.mc.mall.from;/**
 * @author mc
 * @create 2021-04-06 23:06
 **/

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author wangyi
 * Create 2021-04-06 23:06
 * ClassName OrderCreateForm
 * Version 1.0
 */
@Data
public class OrderCreateForm {
    @NotNull
    private Integer shippingId;
}

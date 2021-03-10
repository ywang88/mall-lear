package com.mc.mall.from;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 购物车提交表单
 *
 * @Author wangyi
 * @Date 22:35 2021/1/30
 * @Param
 * @return
 **/
@Data
public class CartAddFrom {

    @NotNull
    private Integer productid;
    private Boolean selectAll = true;

}

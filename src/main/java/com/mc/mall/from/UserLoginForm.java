package com.mc.mall.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Author wangyi
 * Create 2020-11-17 23:13
 * ClassName UserLoginForm
 * Version 1.0
 */
@Data
public class UserLoginForm {

    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 用户密码，MD5加密
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}

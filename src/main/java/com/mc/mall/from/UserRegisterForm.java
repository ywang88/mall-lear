package com.mc.mall.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Author wangyi
 * Create 2020-11-17 22:14
 * ClassName UserForm
 * Version 1.0
 */
@Data
public class UserRegisterForm {
    /**
     * 用户名
     */
    //判断空格
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 用户密码，MD5加密
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String email;

}

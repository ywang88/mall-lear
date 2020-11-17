package com.mc.mall.controller;

import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.consts.MallConst;
import com.mc.mall.from.UserLoginForm;
import com.mc.mall.from.UserRegisterForm;
import com.mc.mall.pojo.MallUser;
import com.mc.mall.service.MallUserService;
import com.mc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Author wangyi
 * Create 2020-11-12 23:02
 * ClassName MallUserController
 * Version 1.0
 */
@RestController
@Slf4j
public class MallUserController {

    @Autowired
    private MallUserService mallUserService;

    //用户注册
    @PostMapping("/user/register")
/*    x-www-form-urlencoded 传值采用@RequestParam
    json传值采用@RequestBody*/
    public ResponseVo register(@Valid @RequestBody UserRegisterForm userForm
            , BindingResult BindingResult) {
        if (BindingResult.hasErrors()) {
            log.error("注册提交的参数有误，{} {}", BindingResult.getFieldError().getField(),
                    BindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(ResponseEnum.PARAM_ERROR, BindingResult);
        }
        MallUser mallUser = new MallUser();
        BeanUtils.copyProperties(userForm, mallUser);
        return mallUserService.register(mallUser);
    }

    //用户登录
    @PostMapping("/user/login")
    public ResponseVo login(@Valid @RequestBody UserLoginForm userLoginForm,
                            BindingResult bindingResult,
                            HttpSession session) {

        if (bindingResult.hasErrors()) {
            return ResponseVo.error(ResponseEnum.PASSWORD_ERROR, bindingResult);
        }
        //获取到用户名与密码
        ResponseVo<MallUser> login = mallUserService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        //设置session
        session.setAttribute(MallConst.CURRE_USER, login.getData());
        return login;
    }

    //查询用户信息
    @GetMapping("/user")
    public ResponseVo<MallUser> userInfor(HttpSession session) {
        MallUser mallUser = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        //判断用户是否存在
        if (mallUser == null) {
            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
        }
        return ResponseVo.success(mallUser);
    }

}

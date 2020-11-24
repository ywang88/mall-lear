package com.mc.mall.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
        log.info("/login sessionId={}", session.getId());
        return login;
    }

    //查询用户信息
    @GetMapping("/user")
    public ResponseVo<MallUser> userInfor(HttpSession session) {
        log.info("/user sessionId={}", session.getId());
        MallUser mallUser = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        //判断用户是否存在，以用拦截器
//        if (mallUser == null) {
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
//        }

        return ResponseVo.success(mallUser);
    }

    // TODO: 2020/11/18
    //判断用户是否在线代码多次用到可以通过拦截器做
    //退出
    @PostMapping("/user/logout")
    public ResponseVo logout(HttpSession session) {
        //打印sessionid
        log.info("/user/logout sessionId={}", session.getId());
        //重session拿到用户信息
        MallUser mallUser = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        //判断是否有用户信息,以用拦截器
//        if (mallUser == null) {
//            //没有则提示用户未登录
//            ResponseVo.error(ResponseEnum.NEED_LOGIN);
//        }
        //有用户信息则清除session
        //session不安全
        session.removeAttribute(MallConst.CURRE_USER);
        return ResponseVo.success();
    }


}

package com.mc.mall.interceptor;

import com.mc.mall.consts.MallConst;
import com.mc.mall.exception.UserLoginException;
import com.mc.mall.pojo.MallUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * true表示继续，false表中断
 * Author wangyi
 * Create 2020-11-18 21:28
 * ClassName UserLoginInterceptor
 * Version 1.0
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle..");
        MallUser mallUser = (MallUser) request.getSession().getAttribute(MallConst.CURRE_USER);
        if (mallUser == null) {
            log.info("malluser=null");
            //拦截后没有返回信息,通过统一异常处理返回定义好的json错误提示
            throw  new UserLoginException();

        }
        return true;
    }
}

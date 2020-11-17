package com.mc.mall.service;

import com.mc.mall.vo.ResponseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.mc.mall.pojo.MallUser;
import com.mc.mall.mapper.MallUserMapper;

public interface MallUserService {
    /* 注册
     * @Author wangyi
     * @Date 22:09 2020/11/12
     * @Param [mallUser]
     * @return void
     **/
    ResponseVo<MallUser> register(MallUser mallUser);

    /* 登录
     * @Author wangyi
     * @Date 22:10 2020/11/12
     * @Param []
     * @return void
     **/
    ResponseVo<MallUser> login(String username, String password);
}

package com.mc.mall.service.impl;

import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.Enum.RoleEnum;
import com.mc.mall.MallApplicationTests;
import com.mc.mall.pojo.MallUser;
import com.mc.mall.service.MallUserService;
import com.mc.mall.vo.ResponseVo;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



@Transactional
public class MallUserServiceImplTest extends MallApplicationTests {
    @Autowired
    private MallUserService mallUserService;

    public static final String USERNAME = "jack";
    public static final String PASSWORD = "jack";

    @Before //前置执行
    public void register() {
        MallUser mallUser = new MallUser(USERNAME, PASSWORD, "jack@qq.com", RoleEnum.CUSTOMER.getCode());
        mallUserService.register(mallUser);
    }

    @Test
    public void login() {
        ResponseVo<MallUser> responseVo = mallUserService.login(USERNAME, PASSWORD);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}
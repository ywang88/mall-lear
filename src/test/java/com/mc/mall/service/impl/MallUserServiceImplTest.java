package com.mc.mall.service.impl;

import com.mc.mall.Enum.RoleEnum;
import com.mc.mall.MallApplicationTests;
import com.mc.mall.pojo.MallUser;
import com.mc.mall.service.MallUserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@Transactional
public class MallUserServiceImplTest extends MallApplicationTests {
    @Autowired
    private MallUserService mallUserService;

    @Test
    public void register() {
        MallUser mallUser = new MallUser("jack", "123456", "jack@qq.com", RoleEnum.CUSTOMER.getCode());
        mallUserService.register(mallUser);
    }
}
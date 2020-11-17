package com.mc.mall.service.impl;

import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.Enum.RoleEnum;
import com.mc.mall.mapper.MallUserMapper;
import com.mc.mall.pojo.MallUser;
import com.mc.mall.service.MallUserService;
import com.mc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.jws.soap.SOAPBinding;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Author wangyi
 * Create 2020-11-12 22:09
 * ClassName MallUserServiceImpl
 * Version 1.0
 */
@Service
public class MallUserServiceImpl implements MallUserService {
    @Autowired
    private MallUserMapper mallUserMapper;

    /*注册
     * @Author wangyi
     * @Date 22:11 2020/11/12
     * @Param [mallUser]
     * @return void
     **/
    @Override
    public ResponseVo<MallUser> register(MallUser mallUser) {
        //查询姓名是否注册
        int conutByUsername = mallUserMapper.conutByUsername(mallUser.getUsername());
        if (conutByUsername > 0) {
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }
        //查询邮箱是否注册
        int conutByEmail = mallUserMapper.conutByEmail(mallUser.getEmail());
        if (conutByEmail > 0) {
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }
        //MD5摘要算法(spring自带)
        mallUser.setPassword(DigestUtils.md5DigestAsHex(
                mallUser.getPassword().getBytes(StandardCharsets.UTF_8)));
        //设置默认角色
        mallUser.setRole(RoleEnum.CUSTOMER.getCode());
        //写入数据库
        int i = mallUserMapper.insertSelective(mallUser);
        if (i == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<MallUser> login(String username, String password) {
        MallUser mallUser = mallUserMapper.selectByUsername(username);
        if (mallUser == null) {
            //用户名不存在(返回用户名密码错误)
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //判断密码与加密后的密码是否一致
        if (!mallUser.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            //密码错误
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        mallUser.setPassword("");
        return ResponseVo.success(mallUser);
    }


}

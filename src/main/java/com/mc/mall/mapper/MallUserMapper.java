package com.mc.mall.mapper;

import com.mc.mall.pojo.MallUser;
import org.apache.catalina.User;

public interface MallUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallUser record);

    int insertSelective(MallUser record);

    MallUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallUser record);

    int updateByPrimaryKey(MallUser record);

    int conutByUsername(String username);

    int conutByEmail(String email);

    MallUser selectByUsername(String username);
}
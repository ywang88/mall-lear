package com.mc.mall.mapper;

import com.mc.mall.from.ShippingForm;
import com.mc.mall.pojo.MallShipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author mc
 * @create 2021-03-10 22:08
 **/
public interface MallShippingMapper {
    //根据用户id与地址id查询
    MallShipping selectByUidAndShippingId(@Param("uid") Integer uid, @Param("shippingId") Integer shippingId);

    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("shippingId") Integer shipingId);

    int insert(MallShipping record);

    int insertSelective(MallShipping record);

    MallShipping selectByPrimaryKeys(Integer id);

    List<MallShipping> selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(MallShipping record);

    int updateByPrimaryKey(MallShipping record);

    List<MallShipping> selectByIdSet(@Param("IdSet") Set IdSet);
}
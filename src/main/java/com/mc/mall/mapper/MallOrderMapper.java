package com.mc.mall.mapper;

import com.mc.mall.pojo.MallOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangyi
 * @since 2021-03-11
 */
public interface MallOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallOrder record);

    int insertSelective(MallOrder record);

    MallOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallOrder record);

    int updateByPrimaryKey(MallOrder record);

    List<MallOrder> selectByUid(Integer uid);

    //根据订单编号查询订单详情
    MallOrder selectByOrderNo(Long orderNo);
}

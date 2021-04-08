package com.mc.mall.service;

import com.github.pagehelper.PageInfo;
import com.mc.mall.from.ShippingForm;
import com.mc.mall.pojo.MallShipping;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mc.mall.vo.ResponseVo;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangyi
 * @since 2021-03-10
 */
public interface IMallShippingService {
    //新增地址
    ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form);

    //修改地址
    ResponseVo update(Integer uid, Integer shippingId, ShippingForm form);

    //删除地址
    ResponseVo delete(Integer uid, Integer shippingId);

    // 收货地址列表

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

}

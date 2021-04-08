package com.mc.mall.service;

import com.github.pagehelper.PageInfo;
import com.mc.mall.pojo.MallOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mc.mall.vo.OrderVo;
import com.mc.mall.vo.ResponseVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangyi
 * @since 2021-03-11
 */
public interface IMallOrderService {
    //创建订单
    ResponseVo<OrderVo> create(Integer uid, Integer shippingId);

    //订单列表
    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

    //订单详情
    ResponseVo<OrderVo> detail(Integer uid, Long orderNo);

    //取消订单
    ResponseVo<OrderVo> cancel(Integer uid, Long orderNo);
}

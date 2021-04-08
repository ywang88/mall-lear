package com.mc.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.MallApplicationTests;
import com.mc.mall.from.CartAddFrom;
import com.mc.mall.from.ShippingForm;
import com.mc.mall.mapper.MallProductMapper;
import com.mc.mall.mapper.MallShippingMapper;
import com.mc.mall.service.ICartService;
import com.mc.mall.service.IMallOrderService;
import com.mc.mall.vo.CartVo;
import com.mc.mall.vo.OrderVo;
import com.mc.mall.vo.ResponseVo;
import junit.framework.TestCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author mc
 * @create 2021-03-22 22:45
 **/
//@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@Slf4j
@Transactional
public class MallOrderServiceImplTest extends MallApplicationTests {

    @Autowired
    private MallProductMapper mallProductMapper;
    @Autowired
    private MallShippingMapper mallShippingMapper;
    @Autowired
    private ICartService iCartService;
    @Autowired
    private IMallOrderService orderService;
    private Integer uid = 2;
    private Integer shippingId = 13;
    private Integer productId = 29;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Before
    public void before() {
        CartAddFrom form = new CartAddFrom();
        form.setProductid(productId);
        form.setSelectAll(true);
        ResponseVo<CartVo> responseVo = iCartService.add(uid, form);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    private ResponseVo<OrderVo> create() {
        ResponseVo<OrderVo> responseVo = orderService.create(uid, shippingId);
        log.info("result={}", gson.toJson(responseVo));
        return responseVo;
    }

    @Test
    public void createTest() {
        ResponseVo<OrderVo> responseVo = create();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @Test
    public void list() {
        ResponseVo<PageInfo> responseVo = orderService.list(uid, 1, 2);
        log.info("result={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void detail() {
        ResponseVo<OrderVo> vo = create();
        ResponseVo<OrderVo> responseVo = orderService.detail(uid, vo.getData().getOrderNo());
        log.info("result={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void cancel() {
        ResponseVo<OrderVo> vo = create();
        ResponseVo<OrderVo> responseVo = orderService.cancel(uid, vo.getData().getOrderNo());
        log.info("result={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}
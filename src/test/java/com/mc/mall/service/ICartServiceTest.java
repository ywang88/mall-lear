package com.mc.mall.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.MallApplicationTests;
import com.mc.mall.from.CartAddFrom;
import com.mc.mall.from.CartUpdateFarm;
import com.mc.mall.vo.CartVo;
import com.mc.mall.vo.ResponseVo;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author mc
 * @create 2021-01-31 0:09
 **/
@Slf4j
public class ICartServiceTest extends MallApplicationTests {

    @Autowired
    private ICartService iCartService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Integer prodectId=26;
    private Integer uid=2;

    @Before
    public void add() {
        log.info("新增购物车[].....");
        CartAddFrom cartAddFrom = new CartAddFrom();
        cartAddFrom.setProductid(prodectId);
        cartAddFrom.setSelectAll(true);
        ResponseVo<CartVo> responseVo = iCartService.add(uid, cartAddFrom);
        log.info("list={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @Test
    public void list() {
        ResponseVo<CartVo> responseVo = iCartService.list(uid);
        log.info("list={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @Test
    public void update() {
        CartUpdateFarm cartUpdateFarm = new CartUpdateFarm();
        cartUpdateFarm.setQuantity(10);
        cartUpdateFarm.setSelected(false);
        ResponseVo<CartVo> voResponseVo = iCartService.update(uid, 26, cartUpdateFarm);
        log.info("cartUpdateFarm={}", gson.toJson(voResponseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), voResponseVo.getStatus());

    }

   @After
    public void delete() {
        log.info("删除购物车[].....");
        ResponseVo<CartVo> responseVo = iCartService.delete(uid, prodectId);
        log.info("responseVo={}", gson.toJson(responseVo));
       Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());


   }
    @Test
    public void selectAll() {
        ResponseVo<CartVo> responseVo = iCartService.selectAll(uid);
        log.info("result={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());


    }  @Test
    public void unselectAll() {
        ResponseVo<CartVo> responseVo = iCartService.unselectAll(uid);
        log.info("result={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());


    }
    @Test
    public void sum() {
        ResponseVo<Integer> responseVo = iCartService.sum(uid);
        log.info("result={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

}
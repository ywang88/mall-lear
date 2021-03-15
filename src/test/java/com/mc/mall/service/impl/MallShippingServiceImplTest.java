package com.mc.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.MallApplicationTests;
import com.mc.mall.from.ShippingForm;
import com.mc.mall.service.IMallShippingService;
import com.mc.mall.vo.ResponseVo;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author mc
 * @create 2021-03-10 22:24
 **/
@Slf4j
public class MallShippingServiceImplTest extends MallApplicationTests {

    @Autowired
    private IMallShippingService mallShippingService;

    public static final Integer uid = 1;
    public static final Integer shippingId = 11;
    private ShippingForm form;

    @Before
    public void before() {
        ShippingForm form = ShippingForm.builder()
                .receiverName("王2二")
                .receiverProvince("四2川1")
                .receiverCity("成2都1")
                .receiverDistrict("2侯区1")
                .receiverAddress("人民2公园1")
                .receiverMobile("010212234651")
                .receiverPhone("1277801714801")
                .receiverZip("266666")
                .build();
        this.form = form;
    }

    @Test
    public void add() {

        ResponseVo<Map<String, Integer>> responseVo = mallShippingService.add(uid, form);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void update() {
//        ShippingForm form = ShippingForm.builder()
//                .receiverName("王二1")
//                .receiverProvince("四1")
//                .receiverCity("成1")
//                .receiverDistrict("武区1")
//                .receiverAddress("人公园1")
//                .receiverMobile("0101234651")
//                .receiverPhone("17781714801")
//                .receiverZip("666661")
//                .build();
        form.setReceiverCity("上海");
        ResponseVo responseVo = mallShippingService.update(uid, shippingId, form);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void delete() {

        ResponseVo<Map<String, Integer>> responseVo = mallShippingService.delete(shippingId, uid);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo responseVo = mallShippingService.list(uid,1,10);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}
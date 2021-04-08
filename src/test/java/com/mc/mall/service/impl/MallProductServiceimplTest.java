package com.mc.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.MallApplicationTests;
import com.mc.mall.service.MallProductService;
import com.mc.mall.vo.ProductDetailVo;
import com.mc.mall.vo.ProductVo;
import com.mc.mall.vo.ResponseVo;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author mc
 * @create 2020-12-03 22:18
 **/
@Slf4j
public class MallProductServiceimplTest extends MallApplicationTests {

    @Autowired
    private MallProductService mallProductService;

    @Test
    public void list() {
        ResponseVo<PageInfo> responseVo = mallProductService.list(null, 1, 1);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @Test
    public void detail() {
        ResponseVo<ProductDetailVo> detail = mallProductService.detail(26);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), detail.getStatus());
    }
}

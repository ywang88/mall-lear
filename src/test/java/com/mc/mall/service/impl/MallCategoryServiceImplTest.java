package com.mc.mall.service.impl;

import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.MallApplicationTests;
import com.mc.mall.service.MallCategoryService;
import com.mc.mall.vo.CatregoryVo;
import com.mc.mall.vo.ResponseVo;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@Slf4j
public class MallCategoryServiceImplTest extends MallApplicationTests {
    @Autowired
    private MallCategoryService mallCategoryService;

    @Test
    public void selectAll() {
        ResponseVo<List<CatregoryVo>> listResponseVo = mallCategoryService.selectAll();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),listResponseVo.getStatus());
    }

    @Test
    public void findSubCategoryId() {
        Set<Integer> set = new HashSet<>();
        mallCategoryService.findSubCategoryId(100001,set);
        log.info("set={}",set);
    }
}

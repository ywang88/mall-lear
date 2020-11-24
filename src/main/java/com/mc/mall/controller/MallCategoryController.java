package com.mc.mall.controller;

import com.mc.mall.service.MallCategoryService;
import com.mc.mall.vo.CatregoryVo;
import com.mc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author wangyi
 * Create 2020-11-18 22:38
 * ClassName MallCategoryController
 * Version 1.0
 */
@RestController
public class MallCategoryController {
    @Autowired
    private MallCategoryService mallCategoryService;

    @GetMapping("/categores")
    public ResponseVo<List<CatregoryVo>> selectAll() {
        return mallCategoryService.selectAll();
    }
}

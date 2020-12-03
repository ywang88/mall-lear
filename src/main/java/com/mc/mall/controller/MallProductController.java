package com.mc.mall.controller;/**
 * @author mc
 * @create 2020-12-03 22:51
 **/

import com.github.pagehelper.PageInfo;
import com.mc.mall.service.MallProductService;
import com.mc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author wangyi
 * Create 2020-12-03 22:51
 * ClassName MallProductController
 * Version 1.0
 */
@RestController
public class MallProductController {
    @Autowired
    private MallProductService mallProductService;

    @GetMapping("/products")
    public ResponseVo<PageInfo> list(@RequestParam(required = false) Integer categoryId
            , @RequestParam(required = false, defaultValue = "1") Integer pageNum
            , @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return mallProductService.list(categoryId, pageNum, pageSize);

    }
}

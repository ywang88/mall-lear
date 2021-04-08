package com.mc.mall.service;

import com.github.pagehelper.PageInfo;
import com.mc.mall.vo.ProductDetailVo;
import com.mc.mall.vo.ProductVo;
import com.mc.mall.vo.ResponseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.mc.mall.mapper.MallProductMapper;
import com.mc.mall.pojo.MallProduct;

import java.util.List;

@Service
public interface MallProductService {
    //查询商品列表
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

    //查询商品详情
    ResponseVo<ProductDetailVo> detail(Integer productId);
}

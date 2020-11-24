package com.mc.mall.service;

import com.mc.mall.vo.CatregoryVo;
import com.mc.mall.vo.ResponseVo;

import java.util.List;

public interface MallCategoryService {
    public ResponseVo<List<CatregoryVo>> selectAll();
}

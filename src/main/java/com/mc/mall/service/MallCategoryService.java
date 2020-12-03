package com.mc.mall.service;

import com.mc.mall.vo.CatregoryVo;
import com.mc.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface MallCategoryService {
    //查询所有分类
    public ResponseVo<List<CatregoryVo>> selectAll();
    //查询所有子类

    void findSubCategoryId(Integer id, Set<Integer> resultSet);
}

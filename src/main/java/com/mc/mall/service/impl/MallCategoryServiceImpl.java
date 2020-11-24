package com.mc.mall.service.impl;

import com.mc.mall.consts.MallConst;
import com.mc.mall.mapper.MallCategoryMapper;
import com.mc.mall.pojo.MallCategory;
import com.mc.mall.service.MallCategoryService;
import com.mc.mall.vo.CatregoryVo;
import com.mc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author wangyi
 * Create 2020-11-18 22:26
 * ClassName MallCategoryServiceImpl
 * Version 1.0
 */
@Service
@Slf4j
public class MallCategoryServiceImpl implements MallCategoryService {
    @Autowired
    private MallCategoryMapper mallCategoryMapper;

    @Override
    public ResponseVo<List<CatregoryVo>> selectAll() {
//        List<CatregoryVo> catregoryVoList = new ArrayList<>();
        List<MallCategory> mallCategories = mallCategoryMapper.selectAll();
        //查出parent_id=0
//        for (MallCategory mallCategory : mallCategories) {
//            if (mallCategory.getParentId().equals(MallConst.ROOT_PARENT_ID)) {
//                CatregoryVo catregoryVo = new CatregoryVo();
//                BeanUtils.copyProperties(mallCategory, catregoryVo);
//                catregoryVoList.add(catregoryVo);
//            }
//        }
        //java8lambda方法
        List<CatregoryVo> catregoryVoList = mallCategories.stream()
                .filter((e) -> e.getParentId().equals(MallConst.ROOT_PARENT_ID))
                .map(this::catregory2CategoryVo)
                 .sorted(Comparator.comparing(CatregoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());

        //调用查询子目录方法
        findSubCategory(catregoryVoList, mallCategories);
        return ResponseVo.success(catregoryVoList);
    }

    //查询子目录方法
    private void findSubCategory(List<CatregoryVo> catregoryVoList, List<MallCategory> categories) {
        for (CatregoryVo catregoryVo : catregoryVoList) {
            List<CatregoryVo> subcatregoryVos = new ArrayList<>();
            for (MallCategory mallCategory : categories) {
                //如果查到内容，设置subCategory,继续往下查
                if (catregoryVo.getId().equals(mallCategory.getParentId())) {
                    CatregoryVo subcatregoryVo1 = catregory2CategoryVo(mallCategory);
                    subcatregoryVos.add(subcatregoryVo1);
                }
                //根据sortorder排序
                subcatregoryVos.sort(Comparator.comparing(CatregoryVo::getSortOrder).reversed());
                catregoryVo.setSubCategories(subcatregoryVos);
                //递归查询下级目录
                findSubCategory(subcatregoryVos, categories);
            }
        }
    }

    private CatregoryVo catregory2CategoryVo(MallCategory mallCategory) {
        CatregoryVo catregoryVo = new CatregoryVo();
        BeanUtils.copyProperties(mallCategory, catregoryVo);
        return catregoryVo;
    }
}

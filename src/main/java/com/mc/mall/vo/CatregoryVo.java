package com.mc.mall.vo;

import lombok.Data;

import java.util.List;

/**
 * Author wangyi
 * Create 2020-11-18 22:16
 * ClassName CatregoryVo
 * Version 1.0
 */
@Data
public class CatregoryVo {
    /**
     * 类别Id
     */
    private Integer id;
    /**
     * 父类别id当id=0时说明是根节点,一级类别
     */
    private Integer parentId;
    /**
     * 类别名称
     */
    private String name;

    /**
     * 排序编号,同类展示顺序,数值相等则自然排序
     */
    private Integer sortOrder;
    private List<CatregoryVo> subCategories;
}

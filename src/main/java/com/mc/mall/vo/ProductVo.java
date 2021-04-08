package com.mc.mall.vo;/**
 * @author mc
 * @create 2020-12-03 21:59
 **/

import lombok.Data;

import java.math.BigDecimal;

/**
 * Author wangyi
 * Create 2020-12-03 21:59
 * ClassName ProductVo
 * Version 1.0
 */
@Data
public class ProductVo {

    private Integer id;

    /**
     * 分类id,对应mall_category表的主键
     */
    private Integer categoryId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品副标题
     */
    private String subtitle;

    /**
     * 产品主图,url相对地址
     */
    private String mainImage;

    /**
     * 价格,单位-元保留两位小数
     */
    private BigDecimal price;


    /**
     * 商品状态.1-在售 2-下架 3-删除
     */
    private Integer status;

}

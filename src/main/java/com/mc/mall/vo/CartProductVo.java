package com.mc.mall.vo;/**
 * @author mc
 * @create 2021-01-30 22:26
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Author wangyi
 * Create 2021-01-30 22:26
 * ClassName CartProductVo
 * Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductVo {
    /**
     * 商品id
     */
    private Integer productid;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品副标题
     */
    private String productSubtitle;
    /**
     * 产品主图,url相对地址
     */
    private String productMainImage;
    /**
     * 价格,单位-元保留两位小数
     */
    private BigDecimal productPrice;
    /**
     * 商品状态.1-在售 2-下架 3-删除
     */
    private Integer productStatus;
    /**
     * 总价格,数量*单价
     */
    private BigDecimal productTotalPrice;
    /**
     * 商品库存
     */
    private Integer productStock;
    /**
     * 是否选中该商品
     */
    private Boolean productSelect;



}



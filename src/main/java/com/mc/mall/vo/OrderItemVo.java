package com.mc.mall.vo;/**
 * @author mc
 * @create 2021-03-15 21:57
 **/

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author wangyi
 * Create 2021-03-15 21:57
 * ClassName OrderItemVo
 * Version 1.0
 */
@Data
public class OrderItemVo {
    @TableField("order_no")
    private Long orderNo;

    /**
     * 商品id
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 商品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 商品图片地址
     */
    @TableField("product_image")
    private String productImage;

    /**
     * 生成订单时的商品单价，单位是元,保留两位小数
     */
    @TableField("current_unit_price")
    private BigDecimal currentUnitPrice;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品总价,单位是元,保留两位小数
     */
    @TableField("total_price")
    private BigDecimal totalPrice;

    @TableField("create_time")
    private Date createTime;

}

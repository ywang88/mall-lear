package com.mc.mall.vo;/**
 * @author mc
 * @create 2021-03-15 21:51
 **/

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.mc.mall.pojo.MallShipping;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Author wangyi
 * Create 2021-03-15 21:51
 * ClassName OrderVo
 * Version 1.0
 */
@Data
public class OrderVo {
    /**
     * 订单号
     */
    @TableField("order_no")
    private Long orderNo;
    /**
     * 实际付款金额,单位是元,保留两位小数
     */
    private BigDecimal payment;
    /**
     * 支付类型,1-在线支付
     */
    @TableField("payment_type")
    private Integer paymentType;
    /**
     * 运费,单位是元
     */
    private Integer postage;
    /**
     * 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
     */
    private Integer status;
    /**
     * 支付时间
     */
    @TableField("payment_time")
    private Date paymentTime;
    /**
     * 发货时间
     */
    @TableField("send_time")
    private Date sendTime;
    /**
     * 交易完成时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 交易关闭时间
     */
    @TableField("close_time")
    private Date closeTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /*
    商品详情
     **/
    private List<OrderItemVo> orderItemVoList;

    private Integer shippingId;
    private MallShipping shippingVo;

}

package com.mc.mall.from;/**
 * @author mc
 * @create 2021-03-10 21:55
 **/

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Author wangyi
 * Create 2021-03-10 21:55
 * ClassName ShippingForm
 * Version 1.0
 */
@Data
@Builder
public class ShippingForm {
    /**
     * 收货姓名
     */
    @NotBlank
    @TableField("receiver_name")
    private String receiverName;

    /**
     * 收货固定电话
     */
    @NotBlank
    @TableField("receiver_phone")
    private String receiverPhone;

    /**
     * 收货移动电话
     */
    @NotBlank
    @TableField("receiver_mobile")
    private String receiverMobile;

    /**
     * 省份
     */
    @NotBlank
    @TableField("receiver_province")
    private String receiverProvince;

    /**
     * 城市
     */
    @NotBlank
    @TableField("receiver_city")
    private String receiverCity;

    /**
     * 区/县
     */
    @NotBlank
    @TableField("receiver_district")
    private String receiverDistrict;

    /**
     * 详细地址
     */
    @NotBlank
    @TableField("receiver_address")
    private String receiverAddress;

    /**
     * 邮编
     */
    @NotBlank
    @TableField("receiver_zip")
    private String receiverZip;

}

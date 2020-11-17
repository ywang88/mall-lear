package com.mc.pay.service;

import com.lly835.bestpay.model.PayResponse;

import java.math.BigDecimal;

public interface IPayService {

    /*创建发起支付*/
    PayResponse create(String orderId, BigDecimal amount);
}

package com.mc.pay.service.ServiceImpl;

import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.mc.pay.service.IPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Author wangyi
 * Create 2020-11-11 21:42
 * ClassName IPayServiceImpl
 * Version 1.0
 */
@Slf4j
@Service
public class IPayServiceImpl implements IPayService {
    /*
     * @Author wangyi
     * @Date 21:43 2020/11/11
     * @Param [orderId, amount]
     * @return void
     **/
    @Override
    public PayResponse create(String orderId, BigDecimal amount) {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId("wxd898fcb01713c658");
        wxPayConfig.setMchId("1483469312");
        wxPayConfig.setMchKey("098F6BCD4621D373CADE4E832627B4F6");
        wxPayConfig.setNotifyUrl("http:127.0.0.1");
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);

        PayRequest payRequest = new PayRequest();
        payRequest.setOrderName("4559066-最好的支付sdk");
        payRequest.setOrderId(orderId);
        payRequest.setOrderAmount(amount.doubleValue());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);
        PayResponse response = bestPayService.pay(payRequest);
        log.info("response={}", response);
        return response;
    }
}

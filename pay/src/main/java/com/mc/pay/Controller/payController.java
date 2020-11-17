package com.mc.pay.Controller;

import com.lly835.bestpay.model.PayResponse;
import com.mc.pay.service.ServiceImpl.IPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Author wangyi
 * Create 2020-11-11 22:09
 * ClassName payController
 * Version 1.0
 */
@Controller
@RequestMapping("/pay")
public class payController {

    @Autowired
    private IPayServiceImpl payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("amount") BigDecimal amount) {
        PayResponse response = payService.create(orderId, amount);
        Map map = new HashMap<>();
        map.put("codeUrl", response.getCodeUrl());
        return new ModelAndView("creat", map);
    }
}

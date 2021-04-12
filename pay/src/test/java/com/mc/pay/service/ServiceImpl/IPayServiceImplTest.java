package com.mc.pay.service.ServiceImpl;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IPayServiceImplTest extends TestCase {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private IPayServiceImpl iPayService;

    @Test
    public void create() {
        iPayService.create("123123123132342312", BigDecimal.valueOf(0.01));
    }


    @Test
    public void sengMessgae(){
        amqpTemplate.convertAndSend("payNotity","hello1");
    }
}
package com.mc.mall.listeneer;/**
 * @author mc
 * @create 2021-04-08 23:25
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Author wangyi
 * Create 2021-04-08 23:25
 * ClassName PayMsgListener
 * Version 1.0
 */
@Component
@Slf4j
@RabbitListener(queues = "payNotity")
public class PayMsgListener {
    @RabbitHandler
    public void persent(String msg) {
        log.info("【接收到消息】=>{}", msg);
    }
}

package com.mc.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {

    @Test
    public void contextLoads() {
        for (int i = 0; i <5 ; i++) {
            System.out.println("sss");
        }
    }

}

package com.virugan;

import com.virugan.context.myLogger;
import com.virugan.service.redisSimpleService;
import com.virugan.tables.mysql.hxsysKemut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 针对于redis的简单测试案例
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = redisTemplExmp.class)
@ComponentScan(basePackages = "com.virugan")
public class redisTemplExmp {

    @Autowired
    redisSimpleService redisSimpleService;
    /**
     * 向redis 进行String类型赋值
     */
    @Test
    public void redisSetString(){
        myLogger.info("=============redisTemplExmp.redisSetString.start=============");

        hxsysKemut hxsyskemut = new hxsysKemut();
        hxsyskemut.setFaredma("001");
        hxsyskemut.setKemuhoo(120011001);
        hxsyskemut.setKemujib(2);
        hxsyskemut.setKemulex("1");
        hxsyskemut.setKemumas("存放其他金融机构资金");
        hxsyskemut.setKemunme("存放同业");
        redisSimpleService.redisSetString(hxsyskemut);

        myLogger.info("=============redisTemplExmp.redisSetString.end=============");
    }
}

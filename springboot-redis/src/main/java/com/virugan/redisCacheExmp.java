package com.virugan;

import com.virugan.context.myLogger;
import com.virugan.service.redisCacheService;
import com.virugan.tables.mysql.hxsysKemut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 使用springboot-cache的方式进行查询，在查询JDBC前先进行缓存查询
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = redisSimpleExmp.class)
@ComponentScan(basePackages = "com.virugan")
public class redisCacheExmp {

    @Autowired
    redisCacheService rediscacheService;

    /**
     * 带缓存进行查询
     */
    @Test
    public void selectWithCache(){
        myLogger.info("=============redisCacheExmp.selectWithCache.start=============");

        hxsysKemut hxsyskemut = new hxsysKemut();
        hxsyskemut.setFaredma("001");
        hxsyskemut.setKemuhoo(120011001);
        rediscacheService.selectWithCache(hxsyskemut);

        myLogger.info("=============redisCacheExmp.selectWithCache.end=============");
    }
}

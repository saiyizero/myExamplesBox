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
     * 带缓存进行单笔查询
     */
    @Test
    public void selectWithCache() throws Exception {
        myLogger.info("=============redisCacheExmp.selectWithCache.start=============");

        hxsysKemut hxsyskemut = new hxsysKemut();
        hxsyskemut.setFaredma("001");
        hxsyskemut.setKemuhoo(120011001);
        rediscacheService.selectWithCache(hxsyskemut);

        myLogger.info("=============redisCacheExmp.selectWithCache.end=============");
    }
    /**
     * 新增数据：调用缓存查询，如果不存在则插入
     * **/
    @Test
    public void InsertData() throws Exception {
        myLogger.info("=============redisCacheExmp.InsertData.start=============");

        hxsysKemut hxsyskemut = new hxsysKemut();
        hxsyskemut.setFaredma("001");
        hxsyskemut.setKemuhoo(120011002);
        hxsyskemut.setKemujib(2);
        hxsyskemut.setKemulex("1");
        hxsyskemut.setKemumas("存放中央银行备付金");
        hxsyskemut.setKemunme("存放央行");
        rediscacheService.insertData(hxsyskemut);

        myLogger.info("=============redisCacheExmp.InsertData.end=============");
    }
}

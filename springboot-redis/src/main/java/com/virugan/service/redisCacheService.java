package com.virugan.service;

import com.virugan.context.myLogger;
import com.virugan.myTemple.MyJdbcTemple;
import com.virugan.myTemple.MyJdbcTempleWithCache;
import com.virugan.myTemple.MyRedisTemple;
import com.virugan.tables.mysql.hxsysKemut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class redisCacheService {
    @Autowired
    MyJdbcTempleWithCache myJdbcTempleWithCache;
    @Autowired
    MyRedisTemple myRedisTemple;

    public void selectWithCache(hxsysKemut hxsyskemut) throws Exception {
        hxsysKemut result = myJdbcTempleWithCache.selectByPrimaryKey(hxsyskemut);
        myLogger.debugToObject(result);
    }
    public void insertData(hxsysKemut hxsyskemut) throws Exception {
        hxsysKemut result = myJdbcTempleWithCache.selectByPrimaryKey(hxsyskemut);
        if(result==null){
            myJdbcTempleWithCache.insert(hxsyskemut);
        }
    }


}

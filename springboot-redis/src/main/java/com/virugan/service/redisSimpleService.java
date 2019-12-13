package com.virugan.service;

import com.virugan.context.myLogger;
import com.virugan.myTemple.MyJdbcTemple;
import com.virugan.myTemple.MyRedisTemple;
import com.virugan.tables.mysql.hxsysKemut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class redisSimpleService {
    @Autowired
    MyJdbcTemple myJdbcTemple;
    @Autowired
    MyRedisTemple myRedisTemple;

    public void redisSetString(hxsysKemut hxsyskemut){

        //按照所给的客户信息查询数据库，如果不存在则插入
        hxsysKemut keyKemut = new hxsysKemut();
        keyKemut.setFaredma(hxsyskemut.getFaredma());
        keyKemut.setKemuhoo(hxsyskemut.getKemuhoo());

        hxsysKemut resultKemut = myJdbcTemple.selectOneToEntity(keyKemut);
        if(resultKemut==null){
            myJdbcTemple.insert(hxsyskemut);
            resultKemut=hxsyskemut;
        }

        //打印selectOneToEntity查询结果
        myLogger.debugToObject("resultKemut",resultKemut);

        String key=hxsyskemut.getClass().getSimpleName()+":"+hxsyskemut.getFaredma()+":"+ hxsyskemut.getKemuhoo();
        //打印拼装后Key值
        myLogger.debug("key",key);
        //将数据同步redis
        myRedisTemple.saveObject(key,resultKemut);
    }

    public void redisGetString(hxsysKemut hxsyskemut){
        hxsysKemut resultKemut = myJdbcTemple.selectOneToEntity(hxsyskemut);
        myLogger.debugToObject("mysql --> resultKemut",resultKemut);

        String key=hxsyskemut.getClass().getSimpleName()+":"+hxsyskemut.getFaredma()+":"+ hxsyskemut.getKemuhoo();
        hxsysKemut kemut = myRedisTemple.findObject(key, hxsysKemut.class);

        myLogger.debugToObject("redis --> resultKemut",resultKemut);

    }
}

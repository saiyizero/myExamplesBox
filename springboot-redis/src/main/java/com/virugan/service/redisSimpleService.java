package com.virugan.service;

import com.virugan.myTemple.MyJdbcTemple;
import com.virugan.myTemple.MyRedisTemple;
import com.virugan.tables.mysql.hxsysKemut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        hxsysKemut resultKemut =(hxsysKemut) myJdbcTemple.selectOneToEntity();
        if(resultKemut==null){
            myJdbcTemple.insert(hxsyskemut);
            resultKemut=hxsyskemut;
        }

        //将数据同步redis
        myRedisTemple.saveObject(keyKemut,resultKemut);
    }
}

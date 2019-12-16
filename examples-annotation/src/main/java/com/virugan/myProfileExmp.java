package com.virugan;

import com.virugan.context.myLogger;
import com.virugan.profile.myAppSource;
import com.virugan.profile.myFtpSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = myProfileExmp.class)
@ComponentScan(basePackages = "com.virugan")
@EnableConfigurationProperties(myFtpSource.class)
public class myProfileExmp {

    @Autowired
    myFtpSource myftpSource;
    @Autowired
    myAppSource myappsource;
    @Test
    public void printFtpEntity() throws Exception {
        myLogger.debugToObject(myftpSource);
        myLogger.debugToObject(myappsource);
    }
}

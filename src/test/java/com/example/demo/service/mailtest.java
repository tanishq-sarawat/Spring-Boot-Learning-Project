package com.example.demo.service;

import com.example.demo.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class mailtest {

    @Autowired
    MailService mailService;
    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void test(){
        userScheduler.sendMail();
    }
}

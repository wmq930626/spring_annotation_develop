package com.wmq.spring.service;

import org.springframework.stereotype.Service;

public class SpringService {
    public Integer print(int a,int b){
        System.out.println("this is a service" + a + b);
        if (a == 1) throw new RuntimeException("测试异常通知");
        return a + b;
    }
}

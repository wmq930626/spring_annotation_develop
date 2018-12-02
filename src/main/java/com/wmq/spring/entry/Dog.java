package com.wmq.spring.entry;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2018/12/2
 * Time:20:17
 */
@Component
public class Dog {
    public Dog() {
    }

    @PostConstruct
    public void init(){
        System.out.println("dog 初始化了");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("dog 销毁了");
    }
}

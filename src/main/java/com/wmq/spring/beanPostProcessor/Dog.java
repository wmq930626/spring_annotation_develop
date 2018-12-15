package com.wmq.spring.beanPostProcessor;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2018/12/2
 * Time:21:43
 */
@Component
public class Dog implements EnvironmentAware {

    @PostConstruct
    public void init(){
        System.out.println("dog 初始化了");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("dog 销毁了");
    }

    public void setEnvironment(Environment environment) {

    }
}

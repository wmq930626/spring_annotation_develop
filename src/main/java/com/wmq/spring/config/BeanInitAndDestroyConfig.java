package com.wmq.spring.config;

import com.wmq.spring.entry.BeanInitAndDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan({"com.wmq.spring.entry"})
public class BeanInitAndDestroyConfig {
    @Bean(initMethod = "init",destroyMethod = "destroy")
    @Lazy
    public BeanInitAndDestroy beanInitAndDestory(){
        return new BeanInitAndDestroy();
    }
}
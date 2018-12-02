package com.wmq.spring.config;

import com.wmq.spring.factorybean.MyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanConfig {
    @Bean
    public MyFactoryBean myFactoryBean(){
        return new MyFactoryBean();
    }
}

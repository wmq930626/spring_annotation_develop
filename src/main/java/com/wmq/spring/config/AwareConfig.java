package com.wmq.spring.config;

import com.wmq.spring.aware.MyAwareBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2019/1/12
 * Time:14:50
 */
@Configuration
public class AwareConfig {
    @Bean
    public MyAwareBean myAwareBean(){
        return new MyAwareBean();
    }
}

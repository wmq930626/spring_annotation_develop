package com.wmq.spring.config;

import com.wmq.spring.aspect.MyAspect;
import com.wmq.spring.service.SpringService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2019/1/20
 * Time:17:50
 */
@Configuration
/**
 * AOP 面向切面编程：只在程序运行期间动态的将某段代码切入到指定方法指定位置运行的编程方式
 */
//开启基于注解式的aop方式
@EnableAspectJAutoProxy
public class MyAopConfig {
    @Bean
    public SpringService springService(){
        return new SpringService();
    }
    @Bean
    public MyAspect myAspect(){
        return new MyAspect();
    }
}

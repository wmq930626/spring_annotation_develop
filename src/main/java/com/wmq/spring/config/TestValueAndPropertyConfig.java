package com.wmq.spring.config;

import com.wmq.spring.entry.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2018/12/13
 * Time:21:47
 */
@Configuration
//使用@PropertySource注解将配置文件的k/v导入到运行时环境中，为Bean属性赋值
@PropertySource({"classpath:/person.properties"})
public class TestValueAndPropertyConfig {
    @Bean
    public Person person(){
        return new Person();
    }
}

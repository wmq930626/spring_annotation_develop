package com.wmq.spring.config;

import com.wmq.spring.entry.Leader;
import com.wmq.spring.entry.Parent;
import com.wmq.spring.selector.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyImportBeanDefinitionRegistrar.class})
public class ImportConfig {
    @Bean
    public Leader leader(){
        return new Leader();
    }

    public Parent parent(){
        return new Parent();
    }
}

package com.wmq.spring.selector;

import com.wmq.spring.entry.Child;
import com.wmq.spring.entry.Person;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry 注册类
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean parent = registry.containsBeanDefinition("parent");
        boolean leader = registry.containsBeanDefinition("leader");
        if (parent){
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Child.class);
            registry.registerBeanDefinition("child",rootBeanDefinition);
        }
        if (leader){
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Person.class);
            registry.registerBeanDefinition("person",rootBeanDefinition);
        }
    }
}

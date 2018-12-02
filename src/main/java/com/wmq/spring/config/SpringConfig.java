package com.wmq.spring.config;

import com.wmq.spring.conditional.AliBaBaConditional;
import com.wmq.spring.conditional.BaiDuConditional;
import com.wmq.spring.entry.Leader;
import com.wmq.spring.entry.Person;
import com.wmq.spring.entry.Student;
import com.wmq.spring.selector.MyImportBeanDefinitionRegistrar;
import com.wmq.spring.selector.MyImportSelector;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration  //等同于以前的bean.xml 告诉spring这是  一个配置类
//等同于<ComponentScan></ComponentScan>标签
//@Conditional({AliBaBaConditional.class})//放在类上面表示不满足条件时真个配置类的配置的所有bean都不注入到ioc中
@ComponentScan(
            //指定扫描那些包
            value = {"com.wmq.spring.service","com.wmq.spring.dao","com.wmq.spring.controller"},
            //过滤那些扫描规则
            /*includeFilters = {
                    //type默认为注解规则
                    // ANNOTATION注解,
                    // ASSIGNABLE_TYPE给定类型,
                    // 	ASPECTJ ASPECTJ表达式,
                    // REGEX正则表达式,
                    // CUSTOM自定义规则
                    @ComponentScan.Filter(classes = {Service.class, Controller.class, Component.class}),
            },*/
            //包含那些扫描规则 优先级高于includeFilters
        includeFilters = {
                    @ComponentScan.Filter(type = FilterType.CUSTOM ,classes = {MyTypeFilter.class})
            }
            //关闭默认扫描规则
            //useDefaultFilters = false
)
@Import({ MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
public class SpringConfig {
    //设置bean是单例还是多例
    //singleton 单例 ,在容器启动时创建bean注入到ioc容器 默认为饿汉式
    //prototype 多例，在获取bean的时候创建对象注入到ioc容器中
    @Scope("singleton")
    //懒加载，只针对单例
    @Lazy
    @Bean("student001")  //等同于配置问价的<Bean></Bean>标签 id默认为方法名，可以再注解中设置id
    public Student student(){
        System.out.println("注入bean到ioc容器中");
        Student student = new Student();
        student.setAge(12);
        student.setName("张三");
        student.setNumber(88888L);
        student.setSex(true);
        return student;
    }

    @Bean("student002")  //等同于配置问价的<Bean></Bean>标签 id默认为方法名，可以再注解中设置id
    public Student student1(){
        Student student = new Student();
        student.setAge(13);
        student.setName("李丽");
        student.setNumber(6666L);
        student.setSex(false);
        return student;
    }
    @Bean("mayun")
    @Conditional({AliBaBaConditional.class})
    public Student  student2(){
        Student student = new Student();
        student.setAge(58);
        student.setName("马云");
        student.setNumber(6666L);
        student.setSex(true);
        return student;
    }

    @Bean("mayun")
    @Conditional({BaiDuConditional.class})
    public Student  student3(){
        Student student = new Student();
        student.setAge(48);
        student.setName("李彦宏");
        student.setNumber(6666L);
        student.setSex(true);
        return student;
    }
}

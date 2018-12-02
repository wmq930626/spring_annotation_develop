package com.wmq.spring.test;

import com.wmq.spring.config.ImportConfig;
import com.wmq.spring.config.SpringConfig;
import com.wmq.spring.entry.Leader;
import com.wmq.spring.entry.Person;
import com.wmq.spring.entry.Student;
import com.wmq.spring.factorybean.MyFactoryBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SpringLearnTest {
    //加载配置类
    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(SpringConfig.class);
    @Test
    /**
     * 测试Configuration 和 Bean注解 以及ComponentScan的一些属性值的租用
     */
    public void TestConfigurationAndBeanAndComponentScan(){
        //通过类型获取Bean
        /*Student student = applicationContext.getBean(Student.class);
        System.out.println(student);*/
        Map<String, Student> beansOfType = APPLICATION_CONTEXT.getBeansOfType(Student.class);
        for (Map.Entry<String, Student> entries :beansOfType.entrySet()){
            System.out.println(entries.getKey()+"-------->"+entries.getValue());
        }
        List<String> beanList = Arrays.asList(APPLICATION_CONTEXT.getBeanNamesForType(Student.class));
        for (String name:beanList) {
            System.out.println(name);
        }
        /*SpringService bean = applicationContext.getBean(SpringService.class);
        bean.print();*/
        String[] beanDefinitionNames = APPLICATION_CONTEXT.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        Object student001 = APPLICATION_CONTEXT.getBean("student001");
        Object student002 = APPLICATION_CONTEXT.getBean("student001");
        System.out.println(student001 == student002);
    }

    /**
     * 测试单例的开启关闭
     */
    @Test
    public void testScope(){
        Object student001 = APPLICATION_CONTEXT.getBean("student001");
        Object student002 = APPLICATION_CONTEXT.getBean("student001");
        System.out.println(student001 == student002);
    }

    /**
     * 测试懒加载，只有当bean的注入方式为单例时有效
     */
    @Test
    public void testLazyLoading(){
        Object student001 = APPLICATION_CONTEXT.getBean("student001");
        Object student002 = APPLICATION_CONTEXT.getBean("student001");
        System.out.println(student001 == student002);
    }

    /**
     * 测试Conditional注解  当满足条件时才加载bean到ioc容器，使用该注解要实现Conditional接口，定义过滤条件
     * 放在类上面表示不满足条件时真个配置类的配置的所有bean都不注入到ioc中
     * 类似还有针对真个ioc容器的过滤方式FilterType.CUSTOM 同样要实现TypeFilter接口定义 过滤规则，貌似只有在包含时生效
     */
    @Test
    public void testConditional(){
        String[] beanDefinitionNames = APPLICATION_CONTEXT.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    /**
     * 测试Import注解，批量注入bean到ioc
     */
    @Test
    public void testImport(){
        String[] beanDefinitionNames = APPLICATION_CONTEXT.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    /**
     * 测试ImportSelector选择器，放在Import注解中，批量注入bean到ioc 自定一个Selector类，选择要注入的bean
     */
    @Test
    public void testImportSelector(){
        Leader bean = APPLICATION_CONTEXT.getBean(Leader.class);
        System.out.println(bean);
    }
    private static final ApplicationContext IMPORT_CONFIG = new AnnotationConfigApplicationContext(ImportConfig.class);
    /**
     * 测试ImportBeanDefinitionRegistrar注册器，放在Import注解中 批量注入bean到ioc 自定一个ImportBeanDefinitionRegistrar类，选择要注入的bean
     */
    @Test
    public void testImportBeanDefinitionRegistrar(){
        String[] beanDefinitionNames = IMPORT_CONFIG.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    private static final ApplicationContext FACTORY_BEAN_CONFIG = new AnnotationConfigApplicationContext(MyFactoryBean.class);

    @Test
    /**
     * 测试factoryBean 创建工厂bean
     */
    public void testFactoryBean(){
        Object myFactoryBean = FACTORY_BEAN_CONFIG.getBean("myFactoryBean");
        System.out.println(myFactoryBean.getClass());

        Object myFactoryBean1 = FACTORY_BEAN_CONFIG.getBean("&myFactoryBean");
        System.out.println(myFactoryBean1.getClass());
    }

}
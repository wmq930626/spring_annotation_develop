package com.wmq.spring.test;

import com.wmq.spring.aware.MyAwareBean;
import com.wmq.spring.beanPostProcessor.MyBeanPostProcessor;
import com.wmq.spring.conditional.AliBaBaConditional;
import com.wmq.spring.config.*;
import com.wmq.spring.entry.*;
import com.wmq.spring.factorybean.MyFactoryBean;
import com.wmq.spring.selector.MyImportBeanDefinitionRegistrar;
import com.wmq.spring.selector.MyImportSelector;
import com.wmq.spring.service.SpringService;
import org.junit.Test;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SpringLearnTest {
    //加载配置类
    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(SpringConfig.class);
    @Test
    /**
     * 测试Configuration 和 Bean注解 以及ComponentScan的一些属性值的使用
     * 用法参见：{@link SpringConfig}
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
     * 测试Conditional注解  当满足条件时才加载bean到ioc容器，使用该注解要实现Condition{@link Condition}接口，定义过滤条件
     * 放在类上面表示不满足条件时真个配置类的配置的所有bean都不注入到ioc中
     * 类似还有针对真个ioc容器的过滤方式FilterType.CUSTOM 同样要实现TypeFilter接口定义 过滤规则，貌似只有在包含时生效
     * 用法参见：{@link AliBaBaConditional}
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
     * 用法参见：{@link ImportConfig}
     */
    @Test
    public void testImport(){
        String[] beanDefinitionNames = APPLICATION_CONTEXT.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    /**
     * 测试ImportSelector选择器，放在Import注解中，批量注入bean到ioc 自定一个{@link ImportSelector}，选择要注入的bean
     * 用法参见：{@link MyImportSelector}
     */
    @Test
    public void testImportSelector(){
        Leader bean = APPLICATION_CONTEXT.getBean(Leader.class);
        System.out.println(bean);
    }
    private static final ApplicationContext IMPORT_CONFIG = new AnnotationConfigApplicationContext(ImportConfig.class);
    /**
     * 测试ImportBeanDefinitionRegistrar注册器，放在Import注解中 批量注入bean到ioc 自定一个ImportBeanDefinitionRegistrar类，选择要注入的bean
     * 用法参见：{@link MyImportBeanDefinitionRegistrar}
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
     * 用法参见：{@link MyFactoryBean}
     */
    public void testFactoryBean(){
        Object myFactoryBean = FACTORY_BEAN_CONFIG.getBean("myFactoryBean");
        System.out.println(myFactoryBean.getClass());

        Object myFactoryBean1 = FACTORY_BEAN_CONFIG.getBean("&myFactoryBean");
        System.out.println(myFactoryBean1.getClass());
    }
    //private static final ApplicationContext BEAN_INIT_AND_DESTORY = new AnnotationConfigApplicationContext(MyFactoryBean.class);

    @Test
    /**
     * 测试bean 的创建、初始化、销毁时间
     *
     * 单例
     *      饿汉式 创建(容器启动时) 初始化（容器启动时） 销毁（容器关闭时）
     *      懒汉式 创建(获取bean时) 初始化（获取bean时） 销毁（容器关闭时）
     *
     * 多例
     *      创建(获取bean时) 初始化（获取bean时） 销毁（容器不负责销毁）
     *
     *  控制bean的初始化销毁方法
     *      1、使用@Bean注解
     *
     *          @Bean(initMethod = "init",destroyMethod = "destroy")
     *          用法参见：{@link BeanInitAndDestroyConfig}
     *
     *      2、让bean实现{@link InitializingBean}（初始化）,{@link DisposableBean}（销毁）接口
     *
     *          用法参见 ： 用法参见：{@link Car}
     *
     *      3、使用JSR250（一下两个注解都是放在bean的方法上）
     *
     *          @PostConstruct：在bean创建完成并且属性赋值完成来初始化bean
     *          @PreDestroy：在容器销毁bean之前来通知我们进行清理工作
     *           用法参见：{@link Dog}
     *
     *       4、使用bean后置处理器 自定义一个BeanPostProcessor实现{@link BeanPostProcessor}
     *              postProcessBeforeInitialization:在bean初始化之前工作
     *              postProcessAfterInitialization：在bean初始化之后工作
     *              用法参见：{@link MyBeanPostProcessor}
     */
    public void testBeanInitAndDestory(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanInitAndDestroyConfig.class);
        Object beanInitAndDestroy = applicationContext.getBean("beanInitAndDestory");
        applicationContext.close();
    }

    @Test
    public void testTestBeanPostProcessor(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBeanPostProcessorConfig .class);
        Object dog = applicationContext.getBean("dog");
    }
    @Test
    public void TestPropertyValue(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestValueAndPropertyConfig.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        Person bean = annotationConfigApplicationContext.getBean(Person.class);
        System.out.println(bean);
    }

    @Test
    public void TestPropertyValue1(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestValueAndPropertyConfig.class);
        ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();
        System.out.println(environment.getProperty("ZHANSAN.SEX"));
    }

    @Test
    public void TestAwareProcessor() throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AwareConfig.class);
        MyAwareBean bean = applicationContext.getBean(MyAwareBean.class);
        ResourceLoader resourceLoader = bean.getResourceLoader();
        System.out.println("resourceLoader:"+resourceLoader);
        Resource resource=resourceLoader.getResource("classpath:aware-test.txt");
        System.out.println("ResourceLoader加载的文件内容是:");
        String line=null;
        BufferedReader reader=new BufferedReader(new InputStreamReader(resource.getInputStream()));
        while((line=reader.readLine())!=null){
            System.out.println(line);
        }
        reader.close();
    }

    @Test
    public void TestAutowired(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoWiredTestConfig.class);
        Car bean = applicationContext.getBean(Car.class);
    }

    @Test
    public void testProfile(){
        //创建一个applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //设置容器的运行环境
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setActiveProfiles("test");
        //注册容器
        applicationContext.register(TestProfileConfig.class);
        //刷新容器
        applicationContext.refresh();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void testAspect(){
        //创建一个applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAopConfig.class);
        SpringService bean = applicationContext.getBean(SpringService.class);
        bean.print(1,2);
    }

}
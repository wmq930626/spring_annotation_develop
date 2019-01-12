package com.wmq.spring.miantest;

import com.wmq.spring.config.BeanInitAndDestroyConfig;
import com.wmq.spring.config.TestBeanPostProcessorConfig;
import com.wmq.spring.entry.BeanInitAndDestroy;
import com.wmq.spring.entry.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2019/1/6
 * Time:14:32
 */
public class TestBeanInitAndDestroy {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanInitAndDestroyConfig.class);
        System.out.println("IOC容器启动了");
        System.out.println("开始获取bean组件了");
        Car bean = applicationContext.getBean(Car.class);

        applicationContext.close();
        System.out.println("IOC容器开始关闭");
    }
}

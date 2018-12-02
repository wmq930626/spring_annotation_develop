package com.wmq.spring.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2018/12/2
 * Time:20:58
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 可以对bean做统一的包装
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization" + o.getClass());
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization" + o.getClass());
        return o;
    }
}

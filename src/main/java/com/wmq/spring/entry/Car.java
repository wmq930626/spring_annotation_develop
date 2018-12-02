package com.wmq.spring.entry;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Car implements InitializingBean,DisposableBean {

    public Car() {
        System.out.println("car 创建了");
    }

    public void destroy() throws Exception {
        System.out.println("car 销毁了");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("car 初始化le");
    }
}

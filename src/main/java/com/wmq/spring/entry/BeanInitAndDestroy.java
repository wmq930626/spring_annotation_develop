package com.wmq.spring.entry;

public class BeanInitAndDestroy {
    public BeanInitAndDestroy() {
        System.out.println("创建了");
    }
    public void init(){
        System.out.println("bean初始化了");
    }
    protected void destroy(){
        System.out.println("bean销毁了");
    }
}

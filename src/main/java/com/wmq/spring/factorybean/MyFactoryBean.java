package com.wmq.spring.factorybean;

import com.wmq.spring.entry.Parent;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Parent> {
    /**
     * 返回bean
     * @return
     * @throws Exception
     */
    public Parent getObject() throws Exception {
        return new Parent();
    }

    /**
     * @return bean的类型
     */
    public Class<?> getObjectType() {
        return Parent.class;
    }

    /**
     * 控制单实例，多实例
     * @return
     */
    public boolean isSingleton() {
        return false;
    }
}

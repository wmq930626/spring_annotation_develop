package com.wmq.spring.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class BaiDuConditional implements Condition {
    /**
     *
     * @param context 判断条件使用的上下文信息
     * @param metadata 注释信息
     * @return
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("company.name");
        System.out.println(property);
        if ("baidu".equals(property)){
            return true;
        }
        return false;
    }
}

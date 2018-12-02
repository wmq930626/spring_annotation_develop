package com.wmq.spring.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyImportSelector implements ImportSelector {

    /**
     *
     * @param importingClassMetadata 当前标注Import注解的类的所有信息
     * @return 为要导入的bean的全类名
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.wmq.spring.entry.Leader"/*,"com.wmq.spring.entry.Person"*/};
    }
}

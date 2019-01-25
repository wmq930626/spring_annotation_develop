package com.wmq.spring.aware;

import org.aspectj.lang.annotation.*;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2019/1/12
 * Time:14:53
 */
public class MyAwareBean implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}

package com.wmq.spring.testautoWiredandqualifier;

import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2018/12/15
 * Time:15:12
 */
@Service("javaJvm")
public class JavaJVM implements BookService {
    public void printBookName() {
        System.out.println("this is book named Java Jvm");
    }
}

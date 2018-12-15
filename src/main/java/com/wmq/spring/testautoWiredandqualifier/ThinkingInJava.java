package com.wmq.spring.testautoWiredandqualifier;

import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2018/12/15
 * Time:15:11
 */
@Service("thinkingInJava")
public class ThinkingInJava implements BookService {
    public void printBookName() {
        System.out.println("this is book named Thinking In Java");
    }
}

package com.wmq.spring.test;

import com.wmq.spring.config.TestAotuWiredAndQualifierConfing;
import com.wmq.spring.testautoWiredandqualifier.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2018/12/15
 * Time:15:29
 */
@ContextConfiguration(classes = {TestAotuWiredAndQualifierConfing.class})
public class TestautoWiredandqualifier extends BasedSpringTest {
    @Autowired
    @Qualifier("thinkingInJava")
    private BookService thinkingInJava;
    //默认就是@Autowired(required=true)，表示注入的时候，该bean必须存在，否则就会注入失败。
    @Autowired(required=false)
    @Qualifier("javaJvm")
    private BookService javaJvm;
    @Test
    public void testAotuWiredAndQualifier(){
        thinkingInJava.printBookName();
        //javaJvm.printBookName();
    }
}

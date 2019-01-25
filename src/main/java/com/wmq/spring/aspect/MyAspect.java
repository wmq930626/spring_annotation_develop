package com.wmq.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * Created with IDEA
 * author:MengQi Wang
 * Date:2019/1/20
 * Time:18:36
 */

/**
 * @Aspect注解告诉spring这是一个通知类
 */
@Aspect
public class MyAspect {
    /**
     * @Pointcut抽取公共的切入点
     */
    @Pointcut("execution(* com.wmq.spring.service.SpringService.print(..))")
    public void pointcut(){}
    //如果使用joinpoint，必须放在方法参数表的第一位
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        //获取方法签名
        String name = joinPoint.getSignature().getName();
        System.out.println("before.........." + name);
        System.out.println("前置通知拿到的方法名" + name);
    }

    @After("execution(* com.wmq.spring.service.SpringService.print(..))")
    public void after(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("after..........");
        System.out.println("后置通知拿到的参数列表" +   Arrays.asList(args));
    }
    //获取返回结果，使用result
    @AfterReturning(value = "pointcut()",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        System.out.println("afterReturning.........." + joinPoint.getSignature().getClass());
        System.out.println("返回通知拿到 的返回值" + result);
    }

    //获取异常
    @AfterThrowing(value = "execution(* com.wmq.spring.service.SpringService.print(..))",throwing = "exception")
    public void afterThrowing(RuntimeException exception){
        System.out.println("afterThrowing..........");
        System.out.println("捕获到的异常" + exception.getMessage());
    }

    @Around("execution(* com.wmq.spring.service.SpringService.print(..))")
    public void arouund(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around 目标方法执行之前");
        Object proceed = null;
        try {
            //通知方法执行
            proceed = joinPoint.proceed();
            System.out.println("around 环绕通知获取的返回值" + proceed);
        } catch (Throwable throwable) {
            System.out.println("around 目标方法异常后执行:" +throwable.getMessage() );
        }
        System.out.println("arouund 目标方法执行之后" );
    }
}

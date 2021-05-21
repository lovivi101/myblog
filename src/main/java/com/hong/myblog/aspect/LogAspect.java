package com.hong.myblog.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 日志切面处理
 * 采用SpringBoot中的AOP进行日志处理，AOP可以以切面的形式拦截，将日志内容记录下来，这里记录以下日志信息：
 * 1. 访问的URL
 * 2. 访问者的IP
 * 3. 访问时调用的方法
 * 4. 访问时传递的参数
 * 5. 访问时返回的内容
 *
 * @Aspect注解：AOP切面作用
 * @Component注解：开启组件扫描，通过注解找到要扫描的对象
 * @Pointcut("execution(* com.star.controller..(..))")：定义切面，申明log()是一个切面，通过execution来表示需要拦截的类，这里表示拦截控制器下面的所有类所有方法
 * RequestLog：将请求的参数封装成一个内部类
 * 在访问页面（controller）之前，拦截请求的URL、IP、调用的方法、传递的参数、返回的内容，并记录到日志
 * @author: hjx
 * @time: 2021年05月10日 11:28
 */

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    //定义一个切面 声明log()是一个切面
    @Pointcut("execution(* com.hong.myblog.controller.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void DoBefore(JoinPoint joinPoint){

        System.out.println("DoBefore.........");
        logger.info("DoBefore.........");
        //获取请求信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        //获取请求方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", requestLog);
    }

    @After("log()")
    public void DoAfter(){
        System.out.println("DoAfter.........");
        logger.info("DoAfter.........");
    }

    //返回之后拦截
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterRuturn(Object result) {
        logger.info("Result : {}", result);
    }


    //封装RequestLog信息
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }
}

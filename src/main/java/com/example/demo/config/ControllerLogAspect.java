package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
@Order(1)
public class ControllerLogAspect {
        @Pointcut("execution(public * com.example.demo.web.*.*(..))")
        public void logPointcut() {}

        @Before("logPointcut()")
        public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //打印请求内容
        log.info("==============请求内容=============");
        log.info("请求地址:{}", request.getRequestURL().toString());
        log.info("请求方式:{}", request.getMethod());
        log.info("请求类方法:{}", joinPoint.getSignature().getName());
        log.info("请求类方法参数:{}", Arrays.toString(joinPoint.getArgs()));
        log.info("==============请求内容=============");
    }


        /**
         * 过程中监测，catch到异常之后返回包装后的错误信息，并打印日志
         */
//    @Around(value = "logPointcut()")
//    public Object catchException(ProceedingJoinPoint joinPoint) {
//        try {
//            return joinPoint.proceed();
//            //todo 在这里catch不同的异常
//        } catch (Throwable e) {
//            String className = joinPoint.getTarget().getClass().getName();
//            String methodName = joinPoint.getSignature().getName();
//            log.warn("在{}的{}方法中,发生了异常{}", className, methodName, e);
//            return BaseResponse.responseFail(null);
//        }
//    }
        @AfterReturning(returning = "o", pointcut = "logPointcut()")
        public void methodAfterReturning(Object o) {
        log.info("===============返回内容===============");
        log.info("返回的内容:{}" + o.toString());
        log.info("===============返回内容===============");
    }


        @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
        public void logThrowing(JoinPoint joinPoint, Throwable e) {
        log.info("***************抛出异常***************");
        log.info("请求类方法:{}", joinPoint.getSignature().getName());
        log.info("异常内容:{}", e);
        log.info("***************抛出异常***************");
    }
}

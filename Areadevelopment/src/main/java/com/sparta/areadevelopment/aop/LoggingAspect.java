package com.sparta.areadevelopment.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j(topic = "LogAop")
public class LoggingAspect {

    @Before("execution(* com.sparta.areadevelopment.controller.*.*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestUrl = request.getRequestURL().toString();
        String httpMethod = request.getMethod();

        log.info("Request URL: {}, HTTP Method: {}", requestUrl, httpMethod);
    }
}
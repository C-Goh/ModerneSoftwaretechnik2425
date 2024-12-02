package com.softwaretechnik.spielekiste.infrastructure.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.softwaretechnik.spielekiste..*(..))")
    public void logMethodExecution() {
        System.out.println("Method execution: " + AopContext.currentProxy().getClass().getName());
    }
}
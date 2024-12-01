package com.softwaretechnik.spielekiste.infrastructure.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.softwaretechnik.spielekiste.infrastructure.aspect.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        System.out.println("Executing aspect: " + joinPoint.getSignature().getName());
    }
}
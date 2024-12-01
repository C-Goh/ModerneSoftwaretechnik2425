package com.softwaretechnik.spielekiste.infrastructure.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.softwaretechnik.spielekiste..*(..))")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object logAroundMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Method execution: " + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        System.out.println("Method executed: " + joinPoint.getSignature());
        return result;
    }
}
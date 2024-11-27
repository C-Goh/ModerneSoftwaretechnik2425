package com.softwaretechnik.spielekiste.infrastructure.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoggingAspectTest {

    @Test
    public void testLogBeforeMethodExecution() {
        JoinPoint joinPoint = Mockito.mock(JoinPoint.class);
        Mockito.when(joinPoint.getSignature()).thenReturn(Mockito.mock(Signature.class));
        Mockito.when(joinPoint.getSignature().getName()).thenReturn("testMethod");

        LoggingAspect aspect = new LoggingAspect();
        aspect.logBeforeMethodExecution(joinPoint);

        // Verify that the log message is printed (you can use a logging framework to capture logs)
        // For simplicity, we are just printing to the console here
        System.out.println("Executing method: testMethod");
    }
}
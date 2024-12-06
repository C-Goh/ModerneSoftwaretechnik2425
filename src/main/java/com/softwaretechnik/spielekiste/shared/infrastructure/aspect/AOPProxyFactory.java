package com.softwaretechnik.spielekiste.shared.infrastructure.aspect;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;

public class AOPProxyFactory {
    public static <T> T createProxy(T target, Advice advice) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(advice);
        return (T) proxyFactory.getProxy();
    }
}
package com.softwaretechnik.spielekiste.game.service;

import com.softwaretechnik.spielekiste.shared.infrastructure.aspect.GamePointsAdvice;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class GameServiceFactory {

    private final UserRepositoryImpl userRepository;

    public GameServiceFactory(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public GameService createGameServiceProxy() {
        // Create the proxy for GameService
        final ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GameService()); // Set the target to your GameService instance

        // Create a pointcut that matches only the endGame method
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("endGame");

        // Add the GamePointsAdvice with the pointcut
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, new GamePointsAdvice(userRepository)));

        return (GameService) proxyFactory.getProxy(); // Return the proxied GameService
    }
}
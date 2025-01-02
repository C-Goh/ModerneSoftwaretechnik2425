package com.softwaretechnik.spielekiste.game.service;

import com.softwaretechnik.spielekiste.shared.infrastructure.aspect.GamePointsAdvice;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import org.springframework.aop.framework.ProxyFactory;

public class GameServiceFactory {

    private final UserRepositoryImpl userRepository;

    public GameServiceFactory(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public GameService createGameServiceProxy() {
        // Create the proxy for GameService
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GameService()); // Set the target to your GameService instance
        proxyFactory.addAdvice(new GamePointsAdvice(userRepository)); // Add the advice

        return (GameService) proxyFactory.getProxy(); // Return the proxied GameService
    }
}
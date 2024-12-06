package com.softwaretechnik.spielekiste.shared.infrastructure.aspect;

import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GamePointsAdvice implements MethodInterceptor {

    private final UserRepository userRepository;

    // Constructor injection for UserRepository
    public GamePointsAdvice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // Retrieve method arguments
        Object[] args = invocation.getArguments();

        int userId = (int) args[0];
        int gameId = (int) args[1];
        int points = (int) args[2];

        // Log the arguments
        System.out.println("Intercepted endGame call:");
        System.out.println("User ID: " + userId + ", Game ID: " + gameId + ", Points: " + points);

        // Save the game points
        userRepository.saveGamePoints(userId, gameId, points);

        // Proceed with the original method
        return invocation.proceed();
    }
}
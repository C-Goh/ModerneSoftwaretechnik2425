package com.softwaretechnik.spielekiste.infrastructure.aspect;

import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;

@Aspect
public class GamePointsAspect {

    private UserRepositoryImpl userRepository;

    public GamePointsAspect(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @After("execution(* com.softwaretechnik.spielekiste.game.service.GameService.*(..))")
    public void saveGamePoints(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length == 3 && args[0] instanceof Integer && args[1] instanceof Integer && args[2] instanceof Integer) {
            Integer userId = (Integer) args[0];
            Integer gameId = (Integer) args[1];
            Integer points = (Integer) args[2];

            userRepository.saveGamePoints(userId, gameId, points);
        } else {
            throw new IllegalArgumentException("Unexpected arguments in endGame method.");
        }
    }
}
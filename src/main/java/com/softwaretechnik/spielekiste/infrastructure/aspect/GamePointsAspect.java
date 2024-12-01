package com.softwaretechnik.spielekiste.infrastructure.aspect;

import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;

@Aspect
public class GamePointsAspect {

    private UserRepositoryImpl userRepository;

    public GamePointsAspect(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @AfterReturning("execution(* com.softwaretechnik.spielekiste.game.service.GameService.endGame(..))")
    public void saveGamePoints(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer userId = (Integer) args[0];
        Integer gameId = (Integer) args[1];
        Integer points = (Integer) args[2];

        userRepository.saveGamePoints(userId, gameId, points);
    }
}
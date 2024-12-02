package com.softwaretechnik.spielekiste.infrastructure.aspect;

import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GamePointsAspect {

    private final UserRepository userRepository;

    public GamePointsAspect(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before("execution(* com.softwaretechnik.spielekiste.game.service.GameService.endGame(..))")
    public void saveGamePoints() {
        if (userRepository != null) {
            // Save game points logic
            System.out.println("Saving game points for user...");
        } else {
            System.out.println("UserRepository not initialized.");
        }
    }
}
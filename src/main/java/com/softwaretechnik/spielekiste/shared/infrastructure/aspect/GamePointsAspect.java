package com.softwaretechnik.spielekiste.shared.infrastructure.aspect;

import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class GamePointsAspect {

    private UserRepository userRepository;

    // Default constructor required by AspectJ weaving
    public GamePointsAspect() {
    }

    // Setter for dependency injection
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @After("execution(* com.softwaretechnik.spielekiste.game.service.GameService.endGame(..))")
    public void saveGamePoints() {
        if (userRepository != null) {
            // Save game points logic
            System.out.println("Saving game points for user...");
        } else {
            System.out.println("UserRepository not initialized.");
        }
    }
}
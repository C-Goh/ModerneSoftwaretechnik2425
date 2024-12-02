package com.softwaretechnik.spielekiste.game.service;

import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final UserRepository userRepository;

    // Konstruktorinjektion, Spring sorgt für die Injektion
    @Autowired
    public GameService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void endGame(int userId, int gameId, int points) {
        if (userId <= 0 || gameId <= 0 || points < 0) {
            throw new IllegalArgumentException("Invalid input for ending the game.");
        }

        // Save the game points, assuming the aspect will handle it
        System.out.println("Game has ended for User ID: " + userId + ", Game ID: " + gameId);
    }
}
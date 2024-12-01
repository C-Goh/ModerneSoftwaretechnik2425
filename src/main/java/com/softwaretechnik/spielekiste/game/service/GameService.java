package com.softwaretechnik.spielekiste.game.service;

import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;

public class GameService {

    private UserRepositoryImpl userRepository;

    public GameService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public void endGame(int userId, int gameId, int points) {
        if (userId <= 0 || gameId <= 0 || points < 0) {
            throw new IllegalArgumentException("Invalid input for ending the game.");
        }

        System.out.println("Game has ended for User ID: " + userId + ", Game ID: " + gameId);
    }
}
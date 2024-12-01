package com.softwaretechnik.spielekiste.game.service;

import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;

public class GameService {

    private UserRepositoryImpl userRepository;

    public GameService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public void endGame(int userId, int gameId, Integer points) {
        // Logic for ending the game
        saveGamePoints(userId, gameId, points);
    }

    private void saveGamePoints(int userId, int gameId, Integer points) {
        userRepository.saveGamePoints(userId, gameId, points);
    }
}
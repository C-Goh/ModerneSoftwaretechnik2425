package com.softwaretechnik.spielekiste.game.service;

import com.softwaretechnik.spielekiste.badge.domain.service.BadgeCondition;
import com.softwaretechnik.spielekiste.badge.domain.service.BadgeDomainService;
import com.softwaretechnik.spielekiste.badge.domain.service.ScoreBadgeCondition;
import com.softwaretechnik.spielekiste.game.domain.Game;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class GameService implements Game {

    private int score;
    private UserEntity user;
    private String gameType;
    private BadgeDomainService<Game> badgeDomainService;


    public GameService() {
        List<BadgeCondition<Game>> badgeConditions = new ArrayList<>();
        badgeConditions.add(new ScoreBadgeCondition<>("scoreBadge1", 1));
        badgeConditions.add(new ScoreBadgeCondition<>("scoreBadge3", 3));
        badgeConditions.add(new ScoreBadgeCondition<>("scoreBadge5", 5)); // Example condition
        badgeConditions.add(new ScoreBadgeCondition<>("scoreBadge10", 10)); // Example condition
        this.badgeDomainService = new BadgeDomainService<>(badgeConditions);
    }

    @Override
    public void start() {
        // Implementation for starting the game
        System.out.println("Game started.");
    }

    @Override
    public void end() {
        // Implementation for ending the game
        System.out.println("Game ended.");
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public UserEntity getUser() {
        return user;
    }

    @Override
    public void setGameBadgesCondition() {

    }

    @Override
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    @Override
    public String getGameType() {
        return this.gameType;
    }

    public void endGame(int userId, int gameId, int points) {
        if (userId <= 0 || gameId <= 0 || points < 0) {
            throw new IllegalArgumentException("Invalid input for ending the game.");
        }

        badgeDomainService.checkAndAwardBadges(this);


        // Save the game points, assuming the aspect will handle it
        System.out.println("Game has ended for User ID: " + userId + ", Game ID: " + gameId);
    }
}

package com.softwaretechnik.spielekiste.game.service;

import com.softwaretechnik.spielekiste.badge.domain.service.BadgeCondition;
import com.softwaretechnik.spielekiste.badge.domain.service.BadgeDomainService;
import com.softwaretechnik.spielekiste.badge.domain.service.ScoreBadgeCondition;
import com.softwaretechnik.spielekiste.game.domain.Game;
import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameService implements Game {

    private int score;
    private UserEntity user;
    private String gameType;
    private BadgeDomainService<Game> badgeDomainService;

    /**
     * TODO 
     * Implement badgeConditions.add() not in GameService but in the GameController 
     * so each game has its own badge conditions 
     */
    public GameService() {
        List<BadgeCondition<Game>> badgeConditions = new ArrayList<>();
        badgeConditions.add(new ScoreBadgeCondition<>("Abzeichen 1", 1));
        badgeConditions.add(new ScoreBadgeCondition<>("Abzeichen 2", 3));
        badgeConditions.add(new ScoreBadgeCondition<>("Abzeichen 3", 5)); // Example condition
        badgeConditions.add(new ScoreBadgeCondition<>("Abzeichen 4", 10)); // Example condition
        badgeConditions.add(new ScoreBadgeCondition<>("Abzeichen 5", 50)); // Example condition
        badgeConditions.add(new ScoreBadgeCondition<>("Abzeichen 6", 100)); // Example condition
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
        int additionalPoints = getUserGamePoints(user.getId(), 1); // Assuming gameId is 1 for this example
        this.score = score + additionalPoints;
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

    private int getUserGamePoints(int userId, int gameId) {
        int userGamePoints = 0;
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT points FROM user_game_points WHERE user_id = ? AND game_id = ?")) {
            statement.setInt(1, userId);
            statement.setInt(2, gameId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userGamePoints = resultSet.getInt("points");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userGamePoints;
    }
}

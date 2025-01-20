package com.softwaretechnik.spielekiste.badge.domain.service;

import com.softwaretechnik.spielekiste.game.domain.Game;
import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BadgeDomainService<T> {
    private final List<BadgeCondition<T>> badgeConditions;

    public BadgeDomainService(List<BadgeCondition<T>> badgeConditions) {
        this.badgeConditions = badgeConditions;
    }

    public void checkAndAwardBadges(Game game) {
        //System.out.println("checkAndAwardBadges called with game: " + game);
        //System.out.println("Badge conditions size: " + badgeConditions.size());

        for (BadgeCondition<T> condition : badgeConditions) {
            //System.out.println("Checking condition: " + condition.getBadgeId());
            if (condition.isConditionMet(game) && !userHasBadge(game.getUser(), condition.getBadgeId())) {
                //System.out.println("Awarding badge: " + condition.getBadgeId() + " to user: " + game.getUser().getId() + " for game type: " + game.getGameType());
                condition.awardBadge(game.getUser(), condition.getBadgeId(), game.getGameType());
            } else {
                //System.out.println("Condition not met or badge already awarded for badge: " + condition.getBadgeId());
            }
        }
    }

    private boolean userHasBadge(UserEntity user, String badgeId) {
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM badges WHERE userId = ? AND badgeId = ? AND hasEarned = 1")) {
            statement.setInt(1, user.getId());
            statement.setString(2, badgeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
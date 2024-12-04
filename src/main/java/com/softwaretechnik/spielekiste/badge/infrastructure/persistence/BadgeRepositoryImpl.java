package com.softwaretechnik.spielekiste.badge.infrastructure.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;
import com.softwaretechnik.spielekiste.badge.domain.repository.BadgeRepository;
import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;

public class BadgeRepositoryImpl implements BadgeRepository {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class.getName());

    @Override
    public BadgeEntity findBadgeById(int id) {
        final String getBadgeSQL = "SELECT * FROM badges WHERE id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getBadgeSQL)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new BadgeEntity(resultSet.getInt("id"), resultSet.getInt("gameID"), 
                resultSet.getString("name"), resultSet.getString("text"), 
                resultSet.getBoolean("hasEarned"), resultSet.getInt("userId"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting badge", e);
        }
        return null;
    }

    @Override
    public BadgeEntity findBadgeByName(String name) {
        final String getBadgeSQL = "SELECT * FROM badges WHERE name = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getBadgeSQL)) {
            preparedStatement.setString(1, name);;
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new BadgeEntity(resultSet.getInt("id"), resultSet.getInt("gameID"), 
                resultSet.getString("name"), resultSet.getString("text"), 
                resultSet.getBoolean("hasEarned"), resultSet.getInt("userId"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting badge", e);
        }
        return null;
    }

    @Override
    public List<BadgeEntity> findAllBadges() {
        final List<BadgeEntity> badges = new ArrayList<>();
        final String getAllBadgesSQL = "SELECT * FROM badges";
        try (Connection connection = SQLiteManager.getConnection();
             Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(getAllBadgesSQL)) {
            while (resultSet.next()) {
                badges.add(new BadgeEntity(resultSet.getInt("id"), resultSet.getInt("gameID"), 
                resultSet.getString("name"), resultSet.getString("text"), 
                resultSet.getBoolean("hasEarned"), resultSet.getInt("userId")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all badges", e);
        }
        return badges;
    }

    @Override
    public List<BadgeEntity> findAllEarnedBadges(int userId, boolean hasEarned) {
        final List<BadgeEntity> badges = new ArrayList<>();
        final String getAllBadgesSQL = "SELECT * FROM badges WHERE userId = ? AND hasEarned = ?";
        try (Connection connection = SQLiteManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAllBadgesSQL)) {
            
            // Setze die Parameter
            preparedStatement.setInt(1, userId);
            preparedStatement.setBoolean(2, hasEarned);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Iteriere durch die Ergebnisse und füge sie zur Liste hinzu
                while (resultSet.next()) {
                    badges.add(new BadgeEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("gameID"),
                        resultSet.getString("name"),
                        resultSet.getString("text"),
                        resultSet.getBoolean("hasEarned"),
                        resultSet.getInt("userId")
                    ));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting badges for userId: " + userId, e);
        }
        return badges;
    }


    @Override
    public List<BadgeEntity> findAllEarnedBadgesbyGame(int userId, int gameId) {
        final List<BadgeEntity> badges = new ArrayList<>();
        final String getAllBadgesSQL = "SELECT * FROM badges WHERE userId = ? AND gameId = ?";
        try (Connection connection = SQLiteManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAllBadgesSQL)) {
            
            // Setze die Parameter
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, gameId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Iteriere durch die Ergebnisse und füge sie zur Liste hinzu
                while (resultSet.next()) {
                    badges.add(new BadgeEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("gameID"),
                        resultSet.getString("name"),
                        resultSet.getString("text"),
                        resultSet.getBoolean("hasEarned"),
                        resultSet.getInt("userId")
                    ));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting badges for userId: " + userId, e);
        }
        return badges;
    }

    
}

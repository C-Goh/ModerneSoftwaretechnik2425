package com.softwaretechnik.spielekiste.badge.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;
import com.softwaretechnik.spielekiste.badge.domain.repository.BadgeRepository;
import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;

public class BadgeRepositoryImpl implements BadgeRepository {

    private static final Logger LOGGER = Logger.getLogger(BadgeRepositoryImpl.class.getName());

    @Override
    public List<BadgeEntity> findAll() {
        List<BadgeEntity> badges = new ArrayList<>();
        String query = "SELECT * FROM badges";
        try (Connection connection = SQLiteManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                badges.add(mapResultSetToBadgeEntity(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding all badges", e);
        }
        return badges;
    }

    @Override
    public Optional<BadgeEntity> findById(int id) {
        String query = "SELECT * FROM badges WHERE id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToBadgeEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding badge by id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<BadgeEntity> findByUserId(int userId) {
        List<BadgeEntity> badges = new ArrayList<>();
        String query = "SELECT * FROM badges WHERE userId = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    badges.add(mapResultSetToBadgeEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding badges by userId", e);
        }
        return badges;
    }

    @Override
    public BadgeEntity save(BadgeEntity badge) {
        String query = "INSERT INTO badges (badgeId, gameType, userId, name, text, condition, hasEarned) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, badge.getBadgeId());
            preparedStatement.setString(2, badge.getGameType());
            preparedStatement.setInt(3, badge.getUserId());
            preparedStatement.setString(4, badge.getName());
            preparedStatement.setString(5, badge.getText());
            preparedStatement.setString(6, badge.getCondition());
            preparedStatement.setBoolean(7, badge.getHasEarned());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        badge.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving badge", e);
        }
        return badge;
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM badges WHERE id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting badge by id", e);
        }
    }

    private BadgeEntity mapResultSetToBadgeEntity(ResultSet resultSet) throws SQLException {
        BadgeEntity badge = new BadgeEntity();
        badge.setId(resultSet.getInt("id"));
        badge.setBadgeId(resultSet.getString("badgeId"));
        badge.setGameType(resultSet.getString("gameType"));
        badge.setUserId(resultSet.getInt("userId"));
        badge.setName(resultSet.getString("name"));
        badge.setText(resultSet.getString("text"));
        badge.setCondition(resultSet.getString("condition"));
        badge.setHasEarned(resultSet.getBoolean("hasEarned"));
        return badge;
    }
}
package com.softwaretechnik.spielekiste.user.infrastructure.persistence;

import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;
import com.softwaretechnik.spielekiste.user.domain.service.UserDomainService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the UserRepository interface for managing user entities in a SQLite database.
 */
public class UserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class.getName());
    private final UserDomainService userDomainService = new UserDomainService();


    @Override
    public void createUser(UserEntity user) {
        try {
            userDomainService.validateUser(user);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return;
        }

        if (findUserByName(user.getName()) != null) {
            LOGGER.log(Level.WARNING, "User already exists with name: {0}", user.getName());
            return;
        }

        final String insertUserSQL = "INSERT INTO users (name) VALUES (?)";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement insertUserStatement = connection.prepareStatement(insertUserSQL)) {
            insertUserStatement.setString(1, user.getName());
            insertUserStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating user", e);
        }
    }

    @Override
    public UserEntity findUserById(int id) {
        final String getUserSQL = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUserSQL)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserEntity(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting user", e);
        }
        return null;
    }

    @Override
    public List<UserEntity> findAllUsers() {
        final List<UserEntity> users = new ArrayList<>();
        final String getAllUsersSQL = "SELECT * FROM users";
        try (Connection connection = SQLiteManager.getConnection();
             Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(getAllUsersSQL)) {
            while (resultSet.next()) {
                users.add(new UserEntity(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all users", e);
        }
        return users;
    }

    @Override
    public void updateUser(UserEntity user) {
        final String updateUserSQL = "UPDATE users SET name = ? WHERE id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateUserSQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user", e);
        }
    }

    @Override
    public void deleteUser(int id) {
        final String deleteUserSQL = "DELETE FROM users WHERE id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting user", e);
        }
    }

    @Override
    public void saveGamePoints(int userId, int gameId, int points) {
        final String sql = "INSERT INTO user_game_points (user_id, game_id, points) VALUES (?, ?, ?) " +
                "ON CONFLICT(user_id, game_id) DO UPDATE SET points = user_game_points.points + excluded.points";

        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setLong(2, gameId);
            statement.setInt(3, points);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserEntity findUserByName(String name) {
        final String getUserSQL = "SELECT * FROM users WHERE name = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUserSQL)) {
            preparedStatement.setString(1, name);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserEntity(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting user by name", e);
        }
        return null;
    }


}
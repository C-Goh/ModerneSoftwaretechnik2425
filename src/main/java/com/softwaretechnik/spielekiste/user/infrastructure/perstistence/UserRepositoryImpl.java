package com.softwaretechnik.spielekiste.infrastructure.persistence.user;

import com.softwaretechnik.spielekiste.domain.user.entity.UserEntity;
import com.softwaretechnik.spielekiste.domain.user.repository.UserRepository;
import com.softwaretechnik.spielekiste.infrastructure.persistence.database.SQLiteManager;

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

    /**
     * Validates the user name.
     *
     * @param name the user name to validate
     * @return true if the name is valid, false otherwise
     */
    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z0-9 ]+");
    }

    /**
     * Finds a user by name.
     *
     * @param name the name of the user to find
     * @return the UserEntity if found, null otherwise
     */
    private UserEntity findUserByName(String name) {
        final String getUserSQL = "SELECT * FROM users WHERE name = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUserSQL)) {
            preparedStatement.setString(1, name);
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
    public void createUser(UserEntity user) {
        if (!isValidName(user.getName())) {
            LOGGER.log(Level.WARNING, "Invalid name: {0}", user.getName());
            return;
        }

        if (findUserByName(user.getName()) != null) {
            LOGGER.log(Level.WARNING, "User already exists with name: {0}", user.getName());
            return;
        }

        if (findUserById(user.getId()) != null) {
            LOGGER.log(Level.WARNING, "User already exists: {0}", user.getName());
            return;
        }

        final String insertUserSQL = "INSERT INTO users (id, name) VALUES (?, ?)";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement insertUserStatement = connection.prepareStatement(insertUserSQL)) {
            insertUserStatement.setInt(1, user.getId());
            insertUserStatement.setString(2, user.getName());
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
}
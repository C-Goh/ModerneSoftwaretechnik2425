package softwaretechnik.user;

import softwaretechnik.database.SQLiteManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private int id;

    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z0-9 ]+");
    }

    public static void createUser(String name) {
        if (!isValidName(name)) {
            LOGGER.log(Level.WARNING, "Invalid name: {0}", name);
            return;
        }

        // Check if user exists
        if (getUser(name) != null) {
            LOGGER.log(Level.WARNING, "User already exists: {0}", name);
            return;
        }

        // Create user if not exists
        final String insertUserSQL = "INSERT INTO users (name) VALUES (?)";
        try (Connection connection = SQLiteManager.getConnection(); PreparedStatement insertUserStatement = connection.prepareStatement(insertUserSQL)) {
            insertUserStatement.setString(1, name);
            insertUserStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating user", e);
        }
    }
    public static void deleteUser(String name) {
        final String deleteUserSQL = "DELETE FROM users WHERE name = ?";
        try (Connection connection = SQLiteManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting user", e);
        }
    }

    public static void updateUser(String oldName, String newName) {
        final String updateUserSQL = "UPDATE users SET name = ? WHERE name = ?";
        try (Connection connection = SQLiteManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(updateUserSQL)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, oldName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user", e);
        }
    }

    public static User getUser(String name) {
        final String getUserSQL = "SELECT * FROM users WHERE name = ?";
        try (Connection connection = SQLiteManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(getUserSQL)) {
            preparedStatement.setString(1, name);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting user", e);
        }
        return null;
    }

}

package softwaretechnik.user;

import softwaretechnik.database.SQLiteManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {

    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z0-9 ]+");
    }

    public static void createUser(String name) {
        if (!isValidName(name)) {
            LOGGER.log(Level.WARNING, "Invalid name: {0}", name);
            return;
        }

        final String insertUserSQL = "INSERT INTO users (name) VALUES (?)";
        try (Connection connection = SQLiteManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating user", e);
        }
    }

}

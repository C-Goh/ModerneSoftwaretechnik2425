package softwaretechnik;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import softwaretechnik.database.SQLiteManager;
import softwaretechnik.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static softwaretechnik.database.SQLiteManager.getConnection;

public class UserTest {

    @BeforeEach
    public void setUp() throws SQLException {
        String tempDatabaseUrl = "jdbc:sqlite:test.db";
        SQLiteManager.setDatabaseUrl(tempDatabaseUrl);
        SQLiteManager.initializeDatabase();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        }
    }

    @Test
    public void testCreateUser() throws SQLException {
        User.createUser("John Doe");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals("John Doe", resultSet.getString("name"));
        }
    }

    @Test
    public void testFailCreateUser() throws SQLException {
        User.createUser("John$&%/");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John$&%/'");
            assertFalse(resultSet.next());
        }
    }

    @Test
    public void createAndDeleteUser() throws SQLException {
        User.createUser("John Doe");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals("John Doe", resultSet.getString("name"));
        }

        User.deleteUser("John Doe");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertFalse(resultSet.next());
        }
    }

    @Test
    public void createAndEditUser() throws SQLException {
        User.createUser("John Doe");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals("John Doe", resultSet.getString("name"));
        }

        User.updateUser("John Doe", "Jane Doe");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'Jane Doe'");
            assertTrue(resultSet.next());
            assertEquals("Jane Doe", resultSet.getString("name"));
        }
    }

    @Test
    public void testFailCreateDuplicateUser() throws SQLException {
        User.createUser("John Doe");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals("John Doe", resultSet.getString("name"));
        }

        User.createUser("John Doe");

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals("John Doe", resultSet.getString("name"));
        }
    }
}
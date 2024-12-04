package com.softwaretechnik.spielekiste.user.domain.repository;


import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.quiz.infrastructure.persistence.QuizRepositoryImpl;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager.getConnection;
import static com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager.initializeDatabase;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        PropertyLoader.loadProperties("src/test/resources/test-application.properties");
        initializeDatabase();
        userRepository = new UserRepositoryImpl();

    }

    @AfterEach
    public void tearDown() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        }
    }

    @Test
    public void testCreateUser() throws SQLException {
        userRepository.createUser(new UserEntity(1, "John Doe"));

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals("John Doe", resultSet.getString("name"));
        }
    }

    @Test
    public void testFailCreateUser() throws SQLException {
        userRepository.createUser(new UserEntity(1, "John$&%/"));

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John$&%/'");
            assertFalse(resultSet.next());
        }
    }

    @Test
    public void createAndDeleteUser() throws SQLException {
        userRepository.createUser(new UserEntity(1, "John Doe"));

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals("John Doe", resultSet.getString("name"));
        }

        userRepository.deleteUser(1);

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertFalse(resultSet.next());
        }
    }

    @Test
    public void createAndEditUser() throws SQLException {
        userRepository.createUser(new UserEntity(1, "John Doe"));

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals("John Doe", resultSet.getString("name"));
        }

        userRepository.updateUser(new UserEntity(1, "Jane Doe"));

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = 'Jane Doe'");
            assertTrue(resultSet.next());
            assertEquals("Jane Doe", resultSet.getString("name"));
        }
    }

    @Test
    public void testFailCreateDuplicateUser() throws SQLException {
        userRepository.createUser(new UserEntity(1, "John Doe"));

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals(1, resultSet.getInt("count"));
        }

        userRepository.createUser(new UserEntity(2, "John Doe"));

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM users WHERE name = 'John Doe'");
            assertTrue(resultSet.next());
            assertEquals(1, resultSet.getInt("count"));
        }
    }
}
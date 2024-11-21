package com.softwaretechnik.spielekiste.infrastructure.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteManager {
    private static String databaseUrl = "jdbc:sqlite:softwaretechnik.db";

    private static final Logger LOGGER = Logger.getLogger(SQLiteManager.class.getName());

    public static void setDatabaseUrl(String url) {
        databaseUrl = url;
    }

    public static Connection getConnection() throws SQLException {
        LOGGER.log(Level.INFO, "Connecting to database: {0}", databaseUrl);
        return DriverManager.getConnection(databaseUrl);
    }

    public static void initializeDatabase() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            final String createUsersTable = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)";
            statement.execute(createUsersTable);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database initialization error", e);
        }
    }
}

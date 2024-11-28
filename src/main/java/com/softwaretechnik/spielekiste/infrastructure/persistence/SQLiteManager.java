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

            final String createCurrentQuestionTable = "CREATE TABLE IF NOT EXISTS current_question (" +
                    "quiz_id INTEGER, " +
                    "question_id INTEGER, " +
                    "question TEXT, " +
                    "answer_options TEXT, " +
                    "correct_answer TEXT)";
            statement.execute(createCurrentQuestionTable);

            final String createQuizResultsTable = "CREATE TABLE IF NOT EXISTS quiz_results (" +
                    "quiz_id INTEGER, " +
                    "points INTEGER, " +
                    "total_questions INTEGER)";
            statement.execute(createQuizResultsTable);

            final String createQuizQuestionsTable = "CREATE TABLE IF NOT EXISTS quiz_questions (" +
                    "quiz_id INTEGER, " +
                    "question TEXT, " +
                    "answer_options TEXT, " +
                    "correct_answer TEXT)";
            statement.execute(createQuizQuestionsTable);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database initialization error", e);
        }
    }
}

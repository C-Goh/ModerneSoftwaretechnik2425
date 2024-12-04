package com.softwaretechnik.spielekiste.infrastructure.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteManager {
    private static final Logger LOGGER = Logger.getLogger(SQLiteManager.class.getName());

    public static Connection getConnection() throws SQLException {
        String databaseUrl = System.getProperty("db.url");
        if (databaseUrl == null || databaseUrl.isEmpty()) {
            throw new IllegalStateException("Database URL is not set");
        }
        return DriverManager.getConnection(databaseUrl);
    }

    public static void initializeDatabase() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            final String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT)";
            statement.execute(createUsersTable);

            final String createUserGamePointsTable = "CREATE TABLE IF NOT EXISTS user_game_points (" +
                    "user_id INTEGER, " +
                    "game_id INTEGER, " +
                    "points INTEGER, " +
                    "FOREIGN KEY(user_id) REFERENCES users(id), " +
                    "PRIMARY KEY(user_id, game_id))";
            statement.execute(createUserGamePointsTable);

            final String createBadgesTable = "CREATE TABLE IF NOT EXISTS badges (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " + // Auto-Inkrement für eindeutige ID
                    "gameId INTEGER NOT NULL, " +              // Spiel-ID
                    "userId INTEGER NOT NULL, " +              // Nutzer-ID
                    "name TEXT NOT NULL, " +                   // Badge-Name
                    "text TEXT, " +                            // Beschreibung des Badges
                    "hasEarned BOOLEAN DEFAULT 0, " +         // Status: Abzeichen verdient, Nicht verliehen (false/0)
                    "FOREIGN KEY(userId) REFERENCES users(id), " + // Fremdschlüssel auf Nutzer
                    "FOREIGN KEY(gameId) REFERENCES games(id))";   // Fremdschlüssel auf Spiele

            statement.execute(createBadgesTable);


            final String createCurrentQuestionTable = "CREATE TABLE IF NOT EXISTS current_question (" +
                    "quiz_id INTEGER, " +
                    "question_id INTEGER, " +
                    "question TEXT, " +
                    "answer_options TEXT, " +
                    "correct_answer TEXT)";
            statement.execute(createCurrentQuestionTable);

            final String createQuizResultsTable = "CREATE TABLE IF NOT EXISTS quiz_results (" +
                    "quiz_id INTEGER, " +
                    "user_id INTEGER, " +
                    "points INTEGER, " +
                    "total_questions INTEGER)";
            statement.execute(createQuizResultsTable);

            final String createQuizQuestionsTable = "CREATE TABLE IF NOT EXISTS quiz_questions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "quiz_id INTEGER, " +
                    "user_id INTEGER, " +
                    "question TEXT, " +
                    "answer_options TEXT, " +
                    "correct_answer TEXT, " +
                    "user_answer TEXT)";
            statement.execute(createQuizQuestionsTable);

            // Insert sample data into quiz_questions table
            statement.execute("DELETE FROM quiz_questions"); // Clear existing data
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, user_id, question, answer_options, correct_answer) VALUES (1, 1, 1, 'What is 2+2?', '2,3,4,5', '4')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, user_id, question, answer_options, correct_answer) VALUES (2, 1, 1, 'What is the capital of France?', 'Berlin,London,Paris,Rome', 'Paris')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, user_id, question, answer_options, correct_answer) VALUES (3, 1, 1, 'What color is the sky on a clear day?', 'Red,Blue,Green,Yellow', 'Blue')");

            // Insert sample data into badges table
            statement.execute("DELETE FROM badges"); // Clear existing data
            statement.execute("INSERT INTO badges (id, gameId, userId, name, text, hasEarned) VALUES (1, 1, 1, 'First Game', 'Play your first Game.')");
            statement.execute("INSERT INTO badges (id, gameId, userId, name, text, hasEarned) VALUES (2, 1, 1, 'New Highscore!', 'Beat your Highscore.')");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database initialization error", e);
        }
    }
}
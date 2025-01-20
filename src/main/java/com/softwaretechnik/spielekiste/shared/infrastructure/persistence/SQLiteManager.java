package com.softwaretechnik.spielekiste.shared.infrastructure.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteManager {
    private static final Logger LOGGER = Logger.getLogger(SQLiteManager.class.getName());

    public static Connection getConnection() throws SQLException {
        final String databaseUrl = System.getProperty("db.url");
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
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "badgeId TEXT NOT NULL, " +
                    "gameType TEXT NOT NULL, " +
                    "userId INTEGER NOT NULL, " +
                    "name TEXT NOT NULL, " +
                    "text TEXT, " +
                    "condition TEXT, " +
                    "hasEarned BOOLEAN DEFAULT 0, " +
                    "FOREIGN KEY(userId) REFERENCES users(id))";
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
                    "question TEXT, " +
                    "answer_options TEXT, " +
                    "correct_answer TEXT)";
            statement.execute(createQuizQuestionsTable);

            final String createUserAnswersTable = "CREATE TABLE IF NOT EXISTS user_answers (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "quiz_id INTEGER, " +
                    "question_id INTEGER, " +
                    "user_id INTEGER, " +
                    "user_answer TEXT, " +
                    "FOREIGN KEY(quiz_id) REFERENCES quiz(id), " +
                    "FOREIGN KEY(question_id) REFERENCES quiz_questions(id), " +
                    "FOREIGN KEY(user_id) REFERENCES users(id))";
            statement.execute(createUserAnswersTable);

            // Insert sample data into quiz_questions table
            statement.execute("DELETE FROM quiz_questions");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (1, 1, 'Was ist 2+2?', '2,3,4,5', '4')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (2, 1, 'Was ist die Hauptstadt von Frankreich?', 'Berlin,London,Paris,Rome', 'Paris')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (3, 1, 'Welche Farbe hat der Himmel an einem klaren Tag?', 'Rot,Blau,Grün,Gelb', 'Blau')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (4, 1, 'Wie viele Tage hat ein Schaltjahr?', '365,366,367,368', '366')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (5, 1, 'Was ist die Hauptstadt von Deutschland?', 'Berlin,Hamburg,München,Köln', 'Berlin')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (6, 1, 'Wie viele Kontinente gibt es?', '5,6,7,8', '7')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (7, 1, 'Was ist die größte Wüste der Welt?', 'Sahara,Gobi,Antarktis,Arabische Wüste', 'Antarktis')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (8, 1, 'Wie viele Planeten hat unser Sonnensystem?', '7,8,9,10', '8')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (9, 1, 'Was ist die chemische Formel für Wasser?', 'H2O,CO2,O2,N2', 'H2O')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (10, 1, 'Wie viele Bundesländer hat Deutschland?', '14,15,16,17', '16')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (11, 1, 'Was ist die Hauptstadt von Italien?', 'Rom,Mailand,Neapel,Turin', 'Rom')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (12, 1, 'Wie viele Zähne hat ein erwachsener Mensch?', '28,30,32,34', '32')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (13, 1, 'Was ist die Hauptstadt von Spanien?', 'Madrid,Barcelona,Valencia,Sevilla', 'Madrid')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (14, 1, 'Wie viele Stunden hat ein Tag?', '22,23,24,25', '24')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (15, 1, 'Was ist die Hauptstadt von Österreich?', 'Wien,Graz,Salzburg,Innsbruck', 'Wien')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (16, 1, 'Wie viele Minuten hat eine Stunde?', '50,55,60,65', '60')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (17, 1, 'Was ist die Hauptstadt von Portugal?', 'Lissabon,Porto,Coimbra,Braga', 'Lissabon')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (18, 1, 'Wie viele Sekunden hat eine Minute?', '50,55,60,65', '60')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (19, 1, 'Was ist die Hauptstadt von Griechenland?', 'Athen,Thessaloniki,Patras,Heraklion', 'Athen')");
            statement.execute("INSERT INTO quiz_questions (id, quiz_id, question, answer_options, correct_answer) VALUES (20, 1, 'Wie viele Monate hat ein Jahr?', '10,11,12,13', '12')");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database initialization error", e);
        }
    }
}
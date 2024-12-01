package com.softwaretechnik.spielekiste.quiz.domain.repository;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.infrastructure.persistence.QuizRepositoryImpl;
import com.softwaretechnik.spielekiste.user.infrastructure.config.PropertyLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager.getConnection;
import static org.junit.jupiter.api.Assertions.*;

public class QuizRepositoryTest {

    private QuizRepositoryImpl quizRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        PropertyLoader.loadProperties("src/test/resources/test-application.properties");
        SQLiteManager.initializeDatabase();
        quizRepository = new QuizRepositoryImpl();

        // Insert sample data into quiz_questions table
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO quiz_questions (quiz_id, question, answer_options, correct_answer) VALUES (1, 'What is 2+2?', '2,3,4,5', '4')");
            statement.execute("INSERT INTO quiz_questions (quiz_id, question, answer_options, correct_answer) VALUES (1, 'What is 3+3?', '5,6,7,8', '6')");
            statement.execute("INSERT INTO quiz_questions (quiz_id, question, answer_options, correct_answer) VALUES (1, 'What is 4+4?', '6,7,8,9', '8')");
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS current_question");
            statement.execute("DROP TABLE IF EXISTS quiz_results");
            statement.execute("DROP TABLE IF EXISTS quiz_questions");
        }
    }

    @Test
    public void testStartQuiz() throws SQLException {
        int quizId = 1;
        quizRepository.startQuiz(quizId);

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM current_question WHERE quiz_id = " + quizId);
            assertTrue(resultSet.next());
        }
    }

    @Test
    public void testGetCurrentQuestion() throws SQLException {
        int quizId = 1;
        String question = "What is 2+2?";
        String[] answerOptions = {"2", "3", "4", "5"};
        String correctAnswer = "4";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO current_question (quiz_id, question, answer_options, correct_answer) VALUES (" + quizId + ", '" + question + "', '" + String.join(",", answerOptions) + "', '" + correctAnswer + "')");
        }

        QuizEntity.Question result = quizRepository.getCurrentQuestion(quizId);
        assertNotNull(result);
        assertEquals(question, result.getQuestion());
        assertEquals(Arrays.asList(answerOptions), result.getAnswerOptions());
        assertEquals(correctAnswer, result.getCorrectAnswer());
    }

    @Test
    public void testCheckAnswer() throws SQLException {
        int quizId = 1;
        String answer = "4";
        String correctAnswer = "4";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO current_question (quiz_id, correct_answer) VALUES (" + quizId + ", '" + correctAnswer + "')");
        }

        String result = quizRepository.checkAnswer(quizId, answer);
        assertEquals("Correct! Well done!", result);
    }

    @Test
    public void testNextQuestion() throws SQLException {
        int quizId = 1;

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO current_question (quiz_id, question_id) VALUES (" + quizId + ", 1)");
        }

        boolean result = quizRepository.nextQuestion(quizId);
        assertTrue(result);
    }

    @Test
    public void testGetFinalResult() throws SQLException {
        int quizId = 1;
        int points = 3;
        int totalQuestions = 3;

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO quiz_results (quiz_id, points, total_questions) VALUES (" + quizId + ", " + points + ", " + totalQuestions + ")");
        }

        String result = quizRepository.getFinalResult(quizId);
        assertEquals("Final result: 3/3 (100.0%)", result);
    }
}
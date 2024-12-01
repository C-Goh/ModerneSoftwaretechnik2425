package com.softwaretechnik.spielekiste.quiz.domain.infrastructure.persistence;

import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.infrastructure.persistence.QuizRepositoryImpl;
import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizRepositoryImplTest {

    private QuizRepositoryImpl quizRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        quizRepository = new QuizRepositoryImpl();
        initializeDatabase();
    }

    private void initializeDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS current_question (quiz_id INTEGER, question_id INTEGER, question TEXT, answer_options TEXT, correct_answer TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS quiz_results (quiz_id INTEGER, user_id INTEGER, points INTEGER, total_questions INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS quiz_questions (id INTEGER PRIMARY KEY AUTOINCREMENT, quiz_id INTEGER, user_id INTEGER, question TEXT, answer_options TEXT, correct_answer TEXT, user_answer TEXT)");
            statement.execute("INSERT INTO quiz_questions (quiz_id, user_id, question, answer_options, correct_answer) VALUES (1, 1, 'What is 2+2?', '2,3,4,5', '4')");
            statement.execute("INSERT INTO quiz_questions (quiz_id, user_id, question, answer_options, correct_answer) VALUES (1, 1, 'What is the capital of France?', 'Berlin,London,Paris,Rome', 'Paris')");
            statement.execute("INSERT INTO quiz_questions (quiz_id, user_id, question, answer_options, correct_answer) VALUES (1, 1, 'What color is the sky on a clear day?', 'Red,Blue,Green,Yellow', 'Blue')");
        }
    }

    @Test
    public void testStartQuiz() {
        QuizEntity quiz = quizRepository.startQuiz(1);
        assertNotNull(quiz);
        assertEquals(1, quiz.getId());
        List<QuizEntity.Question> questions = quiz.getQuestions();
        assertEquals(3, questions.size());
    }

    @Test
    public void testGetCurrentQuestion() {
        QuizEntity.Question question = quizRepository.getCurrentQuestion(1, 1);
        assertNotNull(question);
        assertEquals(1, question.getId());
        assertEquals("What is 2+2?", question.getQuestion());
        assertEquals(List.of("2", "3", "4", "5"), question.getAnswerOptions());
        assertEquals("4", question.getCorrectAnswer());
    }

    @Test
    public void testCheckAnswer() {
        boolean isCorrect = quizRepository.checkAnswer(1, 1, 1, "4");
        assertTrue(isCorrect);
        isCorrect = quizRepository.checkAnswer(1, 1, 1, "3");
        assertFalse(isCorrect);
    }

    @Test
    public void testGetFinalResult() {
        quizRepository.checkAnswer(1, 1, 1, "4");
        quizRepository.checkAnswer(1, 2, 1, "Paris");
        quizRepository.checkAnswer(1, 3, 1, "Blue");
        String result = quizRepository.getFinalResult(1, 1);
        assertEquals("Final result: 3/3 (100%)", result);
    }
}
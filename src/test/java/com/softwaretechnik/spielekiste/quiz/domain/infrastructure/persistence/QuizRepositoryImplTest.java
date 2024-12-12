package com.softwaretechnik.spielekiste.quiz.domain.infrastructure.persistence;

import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.infrastructure.persistence.QuizRepositoryImpl;
import com.softwaretechnik.spielekiste.shared.infrastructure.config.PropertyLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizRepositoryImplTest {

    private QuizRepositoryImpl quizRepository;



    @BeforeEach
    public void setUp() throws SQLException {
        PropertyLoader.loadProperties("src/test/resources/test-application.properties");
        initializeDatabase();
        quizRepository = new QuizRepositoryImpl();

    }

    private void initializeDatabase() throws SQLException {
        PropertyLoader.loadProperties("src/test/resources/test-application.properties");
        SQLiteManager.initializeDatabase();
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
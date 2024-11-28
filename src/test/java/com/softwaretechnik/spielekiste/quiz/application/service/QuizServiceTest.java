package com.softwaretechnik.spielekiste.quiz.application.service;

import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.domain.service.QuizDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

    @Mock
    private QuizDomainService quizDomainService;

    private QuizService quizService;

    @BeforeEach
    public void setUp() {
        quizService = new QuizService(quizDomainService);
    }

    @Test
    public void testStartQuiz() {
        int quizId = 1;
        doNothing().when(quizDomainService).startQuiz(quizId);

        quizService.startQuiz(quizId);

        verify(quizDomainService, times(1)).startQuiz(quizId);
    }

    @Test
    public void testGetCurrentQuestion() {
        int quizId = 1;
        QuizEntity.Question question = new QuizEntity.Question(1, "What is 2+2?", List.of("2", "3", "4", "5"), "4");
        when(quizDomainService.getCurrentQuestion(quizId)).thenReturn(question);

        QuizEntity.Question result = quizService.getCurrentQuestion(quizId);

        assertNotNull(result);
        assertEquals(question, result);
        verify(quizDomainService, times(1)).getCurrentQuestion(quizId);
    }

    @Test
    public void testCheckAnswer() {
        int quizId = 1;
        String answer = "4";
        String expectedResponse = "Correct! Well done!";
        when(quizDomainService.checkAnswer(quizId, answer)).thenReturn(expectedResponse);

        String result = quizService.checkAnswer(quizId, answer);

        assertEquals(expectedResponse, result);
        verify(quizDomainService, times(1)).checkAnswer(quizId, answer);
    }

    @Test
    public void testNextQuestion() {
        int quizId = 1;
        when(quizDomainService.nextQuestion(quizId)).thenReturn(true);

        boolean result = quizService.nextQuestion(quizId);

        assertTrue(result);
        verify(quizDomainService, times(1)).nextQuestion(quizId);
    }

    @Test
    public void testGetFinalResult() {
        int quizId = 1;
        String expectedResult = "Final result: 3/3 (100%)";
        when(quizDomainService.getFinalResult(quizId)).thenReturn(expectedResult);

        String result = quizService.getFinalResult(quizId);

        assertEquals(expectedResult, result);
        verify(quizDomainService, times(1)).getFinalResult(quizId);
    }
}
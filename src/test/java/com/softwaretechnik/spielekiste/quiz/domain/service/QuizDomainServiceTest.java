package com.softwaretechnik.spielekiste.quiz.domain.service;

import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.domain.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuizDomainServiceTest {

    private QuizDomainService quizDomainService;
    private QuizRepository quizRepository;

    @BeforeEach
    public void setUp() {
        quizRepository = Mockito.mock(QuizRepository.class);
        quizDomainService = new QuizDomainService(quizRepository);
    }

    @Test
    public void testStartQuiz() {
        int quizId = 1;
        doNothing().when(quizRepository).startQuiz(quizId);

        quizDomainService.startQuiz(quizId);

        verify(quizRepository, times(1)).startQuiz(quizId);
    }

    @Test
    public void testGetCurrentQuestion() {
        int quizId = 1;
        QuizEntity.Question question = new QuizEntity.Question(1, "What is 2+2?", List.of("2", "3", "4", "5"), "4");
        when(quizRepository.getCurrentQuestion(quizId)).thenReturn(question);

        QuizEntity.Question result = quizDomainService.getCurrentQuestion(quizId);

        assertNotNull(result);
        assertEquals(question, result);
        verify(quizRepository, times(1)).getCurrentQuestion(quizId);
    }

    @Test
    public void testCheckAnswer() {
        int quizId = 1;
        String answer = "4";
        String expectedResponse = "Correct! Well done!";
        when(quizRepository.checkAnswer(quizId, answer)).thenReturn(expectedResponse);

        String result = quizDomainService.checkAnswer(quizId, answer);

        assertEquals(expectedResponse, result);
        verify(quizRepository, times(1)).checkAnswer(quizId, answer);
    }

    @Test
    public void testNextQuestion() {
        int quizId = 1;
        when(quizRepository.nextQuestion(quizId)).thenReturn(true);

        boolean result = quizDomainService.nextQuestion(quizId);

        assertTrue(result);
        verify(quizRepository, times(1)).nextQuestion(quizId);
    }

    @Test
    public void testGetFinalResult() {
        int quizId = 1;
        String expectedResult = "Final result: 3/3 (100%)";
        when(quizRepository.getFinalResult(quizId)).thenReturn(expectedResult);

        String result = quizDomainService.getFinalResult(quizId);

        assertEquals(expectedResult, result);
        verify(quizRepository, times(1)).getFinalResult(quizId);
    }
}
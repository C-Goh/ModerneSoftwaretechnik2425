package com.softwaretechnik.spielekiste.question.application.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softwaretechnik.spielekiste.question.domain.entity.QuestionEntity;
import com.softwaretechnik.spielekiste.question.domain.repository.QuestionRepository;
import com.softwaretechnik.spielekiste.question.domain.service.QuestionDomainService;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionDomainService questionDomainService;

    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        questionService = new QuestionService(questionRepository, questionDomainService);
    }

    @Test
    public void createQuestion() {
        QuestionEntity frage = new QuestionEntity();
        frage.setFrage("Was machen Sachen?");

        questionService.createQuestion(frage);

        verify(questionDomainService).validateQuestion(frage);
        verify(questionRepository).createQuestion(frage);
    }

    @Test
    public void getQuestionById() {
        QuestionEntity frage = new QuestionEntity();
        when(questionRepository.findQuestionById(1)).thenReturn(frage);

        QuestionEntity result = questionService.getQuestionById(1);

        assertEquals(frage, result);
        verify(questionRepository).findQuestionById(1);
    }

    @Test
    public void updateQuestion() {
        QuestionEntity frage = new QuestionEntity();
        frage.setFrage("Was machen Sachen?");

        questionService.updateQuestion(frage);

        verify(questionDomainService).validateQuestion(frage);
        verify(questionRepository).updateQuestion(frage);
    }

    @Test
    public void deleteQuestion() {
        int questionId = 1;
        questionService.deleteQuestion(questionId);

        verify(questionRepository).deleteQuestion(questionId);
    }

    @Test
    public void findAllQuestions() {
        List<QuestionEntity> fragen = List.of(new QuestionEntity(), new QuestionEntity());
        when(questionRepository.findAllQuestions()).thenReturn(fragen);

        List<QuestionEntity> result = questionService.findAllQuestions();

        assertEquals(fragen, result);
        verify(questionRepository).findAllQuestions();
    }
    
}

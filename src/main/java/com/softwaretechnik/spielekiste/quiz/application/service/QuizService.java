package com.softwaretechnik.spielekiste.quiz.application.service;

import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.domain.service.QuizDomainService;

public class QuizService {

    private final QuizDomainService quizDomainService;

    public QuizService(QuizDomainService quizDomainService) {
        this.quizDomainService = quizDomainService;
    }

    public void startQuiz(int quizId) {
        quizDomainService.startQuiz(quizId);
    }

    public QuizEntity.Question getCurrentQuestion(int quizId) {
        return quizDomainService.getCurrentQuestion(quizId);
    }

    public String checkAnswer(int quizId, String answer) {
        return quizDomainService.checkAnswer(quizId, answer);
    }

    public boolean nextQuestion(int quizId) {
        return quizDomainService.nextQuestion(quizId);
    }

    public String getFinalResult(int quizId) {
        return quizDomainService.getFinalResult(quizId);
    }
}
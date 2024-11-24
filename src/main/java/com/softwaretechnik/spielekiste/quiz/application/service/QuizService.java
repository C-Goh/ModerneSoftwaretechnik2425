package com.softwaretechnik.spielekiste.quiz.application.service;

import com.softwaretechnik.spielekiste.quiz.domain.repository.QuizRepository;
import com.softwaretechnik.spielekiste.quiz.domain.service.QuizDomainService;

public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizDomainService quizDomainService;

    public QuizService(QuizRepository quizRepository, QuizDomainService quizDomainService) {
        this.quizRepository = quizRepository;
        this.quizDomainService = quizDomainService;
    }

    public boolean checkAnswer(int quizId, String answer) {
        // Additional business logic can be added here
        return quizDomainService.checkAnswer(quizId, answer);
    }

    public String getQuizQuestion(int quizId) {
        // Additional business logic can be added here
        return quizDomainService.getQuizQuestion(quizId);
    }

    public String nextQuestion(int quizId) {
        // Additional business logic can be added here
        return quizDomainService.nextQuestion(quizId);
    }

    public int getFinalResult(int quizId) {
        // Additional business logic can be added here
        return quizDomainService.getFinalResult(quizId);
    }
}
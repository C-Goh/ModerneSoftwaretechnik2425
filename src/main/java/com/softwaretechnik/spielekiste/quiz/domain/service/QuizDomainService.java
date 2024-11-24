package com.softwaretechnik.spielekiste.quiz.domain.service;

import com.softwaretechnik.spielekiste.quiz.domain.repository.QuizRepository;

public class QuizDomainService {
    private final QuizRepository quizRepository;

    public QuizDomainService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public boolean checkAnswer(int quizId, String answer) {
        // Additional business logic can be added here
        return quizRepository.checkAnswer(answer);
    }

    public String getQuizQuestion(int quizId) {
        // Additional business logic can be added here
        return quizRepository.getQuizQuestion(quizId);
    }

    public String nextQuestion(int quizId) {
        // Additional business logic can be added here
        return quizRepository.nextQuestion(quizId);
    }

    public int getFinalResult(int quizId) {
        // Additional business logic can be added here
        return quizRepository.getFinalResult(quizId);
    }
}
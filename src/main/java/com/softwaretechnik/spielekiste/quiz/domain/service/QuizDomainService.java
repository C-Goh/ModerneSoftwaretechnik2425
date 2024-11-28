package com.softwaretechnik.spielekiste.quiz.domain.service;

import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.domain.repository.QuizRepository;

public class QuizDomainService {
    private final QuizRepository quizRepository;

    public QuizDomainService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public void startQuiz(int quizId) {
        quizRepository.startQuiz(quizId);
    }

    public QuizEntity.Question getCurrentQuestion(int quizId) {
        return quizRepository.getCurrentQuestion(quizId);
    }

    public String checkAnswer(int quizId, String answer) {
        return quizRepository.checkAnswer(quizId, answer);
    }

    public boolean nextQuestion(int quizId) {
        return quizRepository.nextQuestion(quizId);
    }

    public String getFinalResult(int quizId) {
        return quizRepository.getFinalResult(quizId);
    }
}
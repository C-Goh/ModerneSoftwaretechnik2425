package com.softwaretechnik.spielekiste.quiz.domain.repository;

import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;

public interface QuizRepository {
    boolean checkAnswer(String answer);

    String getQuizQuestion(int quizId);

    String nextQuestion(int quizId);

    int getFinalResult(int quizId);
}
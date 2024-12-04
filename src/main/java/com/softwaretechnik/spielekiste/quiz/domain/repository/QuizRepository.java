package com.softwaretechnik.spielekiste.quiz.domain.repository;

import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;

public interface QuizRepository {
    QuizEntity startQuiz(int quizId);

    QuizEntity.Question getCurrentQuestion(int quizId, int questionId);

    boolean checkAnswer(int quizId, int questionId, int userId, String answer);

    String getFinalResult(int quizId, int userId);

    int getFinalPoints(int quizId, int userId);
}
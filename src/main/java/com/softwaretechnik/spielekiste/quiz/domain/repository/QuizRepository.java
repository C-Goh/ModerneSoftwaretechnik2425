package com.softwaretechnik.spielekiste.quiz.domain.repository;

import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;

public interface QuizRepository {
    void startQuiz(int quizId);

    QuizEntity.Question getCurrentQuestion(int quizId);

    String checkAnswer(int quizId, String answer);

    boolean nextQuestion(int quizId);

    String getFinalResult(int quizId);
}
package com.softwaretechnik.spielekiste.domain.question.repository;

import com.softwaretechnik.spielekiste.domain.question.entity.QuestionEntity;

import java.util.List;

public interface QuestionRepository {
    void createQuestion(QuestionEntity frage);

    QuestionEntity findQuestionById(int id);

    List<QuestionEntity> findAllQuestions();

    void updateQuestion(QuestionEntity frage);

    void deleteQuestion(int id);
}

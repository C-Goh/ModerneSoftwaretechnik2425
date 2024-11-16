package com.softwaretechnik.spielekiste.question.domain.repository;

import java.util.List;

import com.softwaretechnik.spielekiste.question.domain.entity.QuestionEntity;

public interface QuestionRepository {
    void createQuestion(QuestionEntity frage);

    QuestionEntity findQuestionById(int id);

    List<QuestionEntity> findAllQuestions();

    void updateQuestion(QuestionEntity frage);

    void deleteQuestion(int id);
}

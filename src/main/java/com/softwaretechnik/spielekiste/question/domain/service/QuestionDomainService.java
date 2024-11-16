package com.softwaretechnik.spielekiste.question.domain.service;

import com.softwaretechnik.spielekiste.question.domain.entity.QuestionEntity;

public class QuestionDomainService {

    public void validateUser(QuestionEntity frage) {
        if (frage.getFrage() == null || frage.getFrage().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be null or empty");
        }
        if (frage.getAntwort() == null || frage.getAntwort().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be null or empty");
        }
    }

}
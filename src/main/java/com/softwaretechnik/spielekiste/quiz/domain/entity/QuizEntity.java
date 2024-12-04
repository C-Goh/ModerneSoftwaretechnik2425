package com.softwaretechnik.spielekiste.quiz.domain.entity;

import java.util.List;

public class QuizEntity {
    private final int id;
    private final List<Question> questions;

    public QuizEntity(int id, List<Question> questions) {
        this.id = id;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public static class Question {
        private final int id;
        private final String question;
        private final List<String> answerOptions;
        private final String correctAnswer;

        public Question(int id, String question, List<String> answerOptions, String correctAnswer) {
            this.id = id;
            this.question = question;
            this.answerOptions = answerOptions;
            this.correctAnswer = correctAnswer;
        }

        public int getId() {
            return id;
        }

        public String getQuestion() {
            return question;
        }

        public List<String> getAnswerOptions() {
            return answerOptions;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
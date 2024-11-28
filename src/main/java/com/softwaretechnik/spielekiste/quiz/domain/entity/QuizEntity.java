package com.softwaretechnik.spielekiste.quiz.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class QuizEntity {
    private int id;
    private List<Question> questionCatalog;  // All available questions
    private List<Question> questionList;     // The selected 3 questions
    private int currentQuestionIndex;
    private int points;

    public QuizEntity(int id, List<Question> questionCatalog) {
        this.id = id;
        this.questionCatalog = questionCatalog;
        this.questionList = new ArrayList<>();
        this.currentQuestionIndex = 0;
        this.points = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getQuestionCatalog() {
        return questionCatalog;
    }

    public void setQuestionCatalog(List<Question> questionCatalog) {
        this.questionCatalog = questionCatalog;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public static class Question {
        private int id;
        private String question;
        private List<String> answerOptions;
        private String correctAnswer;

        public Question(int id, String question, List<String> answerOptions, String correctAnswer) {
            this.id = id;
            this.question = question;
            this.answerOptions = answerOptions;
            this.correctAnswer = correctAnswer;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public List<String> getAnswerOptions() {
            return answerOptions;
        }

        public void setAnswerOptions(List<String> answerOptions) {
            this.answerOptions = answerOptions;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
        }
    }
}
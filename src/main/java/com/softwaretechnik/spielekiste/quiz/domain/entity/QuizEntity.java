package com.softwaretechnik.spielekiste.quiz.domain.entity;

public class QuizEntity {
    private int id;
    private String currentQuestion;
    private int totalQuestions;
    private int roundResult;

    public QuizEntity() {
    }

    public QuizEntity(int id, String currentQuestion, int totalQuestions, int roundResult) {
        this.id = id;
        this.currentQuestion = currentQuestion;
        this.totalQuestions = totalQuestions;
        this.roundResult = roundResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(int roundResult) {
        this.roundResult = roundResult;
    }
}
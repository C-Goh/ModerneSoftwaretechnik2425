package com.softwaretechnik.spielekiste.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class QuizController {

    @FXML
    private Label questionLabel;

    @FXML
    private Button answerButton1;

    @FXML
    private Button answerButton2;

    @FXML
    private Button answerButton3;

    @FXML
    private Button answerButton4;

    @FXML
    private Label scoreLabel;

    private int score = 0;

    @FXML
    public void initialize() {
        loadNextQuestion();
    }

    @FXML
    private void handleAnswerButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        checkAnswer(clickedButton.getText());
        loadNextQuestion();
    }

    private void loadNextQuestion() {
        // Load the next question and answers
        // This is just a placeholder. Replace with actual question loading logic.
        questionLabel.setText("What is the capital of France?");
        answerButton1.setText("Paris");
        answerButton2.setText("London");
        answerButton3.setText("Berlin");
        answerButton4.setText("Madrid");
    }

    private void checkAnswer(String selectedAnswer) {
        // Check if the selected answer is correct
        // This is just a placeholder. Replace with actual answer checking logic.
        if ("Paris".equals(selectedAnswer)) {
            score++;
        }
        updateScore();
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + score);
    }
}
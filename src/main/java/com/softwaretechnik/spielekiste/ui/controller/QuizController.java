package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.game.service.GameService;
import com.softwaretechnik.spielekiste.game.service.GameServiceFactory;
import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.infrastructure.persistence.QuizRepositoryImpl;
import com.softwaretechnik.spielekiste.shared.infrastructure.aspect.GamePointsAdvice;
import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.aop.framework.ProxyFactory;
import javafx.scene.input.MouseEvent;

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

    final int userId = UserContext.getCurrentUser().getId();
    final int gameId = 1;
    private int score = 0;
    private int currentQuestionIndex = 0;
    private QuizEntity quiz;
    private QuizRepositoryImpl quizRepository = new QuizRepositoryImpl();
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private GameService gameService;


    @FXML
    public void initialize() {
        GameServiceFactory gameServiceFactory = new GameServiceFactory(userRepository);
        gameService = gameServiceFactory.createGameServiceProxy();

        startQuiz();
        loadNextQuestion();
    }

    @FXML
    private void handleAnswerButtonClick(javafx.event.ActionEvent event) {
        final Button clickedButton = (Button) event.getSource();
        checkAnswer(clickedButton.getText());
        loadNextQuestion();
    }

    private void startQuiz() {
        final int quizId = 1; // Example quiz ID
        quiz = quizRepository.startQuiz(quizId);
    }

    private void loadNextQuestion() {
        if (quiz != null && currentQuestionIndex < quiz.getQuestions().size()) {
            final QuizEntity.Question question = quiz.getQuestions().get(currentQuestionIndex);
            questionLabel.setText(question.getQuestion());
            answerButton1.setText(question.getAnswerOptions().get(0));
            answerButton2.setText(question.getAnswerOptions().get(1));
            answerButton3.setText(question.getAnswerOptions().get(2));
            answerButton4.setText(question.getAnswerOptions().get(3));
            currentQuestionIndex++;
        } else {
            // No more questions, show final result
            questionLabel.setText("Quiz completed!");
            answerButton1.setDisable(true);
            answerButton2.setDisable(true);
            answerButton3.setDisable(true);
            answerButton4.setDisable(true);
            scoreLabel.setText(quizRepository.getFinalResult(quiz.getId(), userId));

            final int points = quizRepository.getFinalPoints(quiz.getId(), userId);

            gameService.endGame(userId, gameId, points);
        }
    }

    private void checkAnswer(String selectedAnswer) {
        if (quiz != null && currentQuestionIndex > 0) {
            final QuizEntity.Question currentQuestion = quiz.getQuestions().get(currentQuestionIndex - 1);
            final boolean isCorrect = quizRepository.checkAnswer(quiz.getId(), currentQuestion.getId(), userId, selectedAnswer);
            if (isCorrect) {
                score++;
            }
            updateScore();
        }
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + score);
    }

    @FXML
    private void loadGameOverview(MouseEvent event) {
        PageLoader.getInstance().loadGameOverviewPage(event);
    }
}
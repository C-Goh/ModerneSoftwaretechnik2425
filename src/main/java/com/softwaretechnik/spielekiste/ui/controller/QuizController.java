package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.game.service.GameService;
import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.infrastructure.persistence.QuizRepositoryImpl;
import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

@Controller
public class QuizController {

    private final GameService gameService;
    private final UserRepository userRepository;
    private final QuizRepositoryImpl quizRepository;

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
    private int currentQuestionIndex = 0;
    private QuizEntity quiz;

    final int userId = UserContext.getCurrentUser().getId();
    final int gameId = 1;

    // Konstruktorinjektion, um die Abhängigkeiten korrekt zu injizieren
    @Autowired
    public QuizController(GameService gameService, UserRepository userRepository, QuizRepositoryImpl quizRepository) {
        this.gameService = gameService;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    @FXML
    public void initialize() {
        startQuiz();
        loadNextQuestion();
    }

    @FXML
    private void handleAnswerButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        checkAnswer(clickedButton.getText());
        loadNextQuestion();
    }

    private void startQuiz() {
        int quizId = 1; // Beispiel Quiz-ID
        quiz = quizRepository.startQuiz(quizId);  // Verwende das QuizRepository
    }

    private void loadNextQuestion() {
        if (quiz != null && currentQuestionIndex < quiz.getQuestions().size()) {
            QuizEntity.Question question = quiz.getQuestions().get(currentQuestionIndex);
            questionLabel.setText(question.getQuestion());
            answerButton1.setText(question.getAnswerOptions().get(0));
            answerButton2.setText(question.getAnswerOptions().get(1));
            answerButton3.setText(question.getAnswerOptions().get(2));
            answerButton4.setText(question.getAnswerOptions().get(3));
            currentQuestionIndex++;
        } else {
            // Keine weiteren Fragen, zeige das Endergebnis an
            questionLabel.setText("Quiz abgeschlossen!");
            answerButton1.setDisable(true);
            answerButton2.setDisable(true);
            answerButton3.setDisable(true);
            answerButton4.setDisable(true);
            scoreLabel.setText(quizRepository.getFinalResult(quiz.getId(), userId));

            int points = quizRepository.getFinalPoints(quiz.getId(), userId);
            gameService.endGame(userId, gameId, points);
        }
    }

    private void checkAnswer(String selectedAnswer) {
        if (quiz != null && currentQuestionIndex > 0) {
            QuizEntity.Question currentQuestion = quiz.getQuestions().get(currentQuestionIndex - 1);
            boolean isCorrect = quizRepository.checkAnswer(quiz.getId(), currentQuestion.getId(), userId, selectedAnswer);
            if (isCorrect) {
                score++;
            }
            updateScore();
        }
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + score);
    }
}
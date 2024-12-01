package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverviewController {

    @FXML
    private ImageView profileImageView;

    @FXML
    private ImageView badgeIcon;

    @FXML
    private ImageView backButton;

    @FXML
    private Label helloLabel;

    @FXML
    public void initialize() {
        final String name = UserContext.getCurrentUser().getName();
        setHelloText(name);
    }

    public void setHelloText(String name) {
        helloLabel.setText("hello " + name);
    }

    @FXML
    private void loadAchievementsPage(MouseEvent event) {
        System.out.println("Badge icon clicked. Loading achievements page.");
        // Implement logic to switch to the achievements page.
    }

    @FXML
    private void loadStartPage(MouseEvent event) {
        System.out.println("Back button clicked. Loading start page.");
        // Implement logic to switch to the start page.
    }

    @FXML
    private void loadQuizPage(MouseEvent event) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/quiz.fxml"));
            final Parent root = loader.load();
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800 , 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
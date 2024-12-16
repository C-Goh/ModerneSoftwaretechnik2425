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
    private void loadBadgeOverviewPage(MouseEvent event) {
        PageLoader.getInstance().loadBadgeOverviewPage(event);
    }

    @FXML
    private void loadStartPage(MouseEvent event) {
        PageLoader.getInstance().loadStartPage(event);
    }

    @FXML
    private void loadQuizPage(MouseEvent event) {
        PageLoader.getInstance().loadQuizPage(event);
    }
}
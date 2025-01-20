package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import com.softwaretechnik.spielekiste.user.application.service.UserService;
import com.softwaretechnik.spielekiste.user.domain.service.UserDomainService;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameOverviewController {

    @FXML
    private ImageView profileImageView;

    @FXML
    private ImageView badgeIcon;

    @FXML
    private ImageView backButton;

    @FXML
    private Label helloLabel;

    private UserService userService;

    public GameOverviewController() {
        this.userService = new UserService(new UserRepositoryImpl(), new UserDomainService());
    }

    @FXML
    public void initialize() {
        final String name = UserContext.getCurrentUser().getName();
        setHelloText(name);
    }

    public void setHelloText(String name) {
        helloLabel.setText("Hallo " + name);
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

    @FXML
    private void deleteUser(MouseEvent event) {
        final int userId = UserContext.getCurrentUser().getId();
        userService.deleteUser(userId);
        UserContext.setCurrentUser(null);
        PageLoader.getInstance().loadStartPage(event);
    }
}
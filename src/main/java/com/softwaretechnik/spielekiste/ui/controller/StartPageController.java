package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;

public class StartPageController {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private void handleCreateProfile(MouseEvent event) {
        try {
            // Load CreateProfile.fxml
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/CreateProfile.fxml"));
            final Parent root = loader.load();
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        final UserRepositoryImpl userRepository = new UserRepositoryImpl();
        final List<UserEntity> users = userRepository.findAllUsers();

        if (!users.isEmpty()) {
            button1.setText(users.getFirst().getName());
            button1.setUserData(users.getFirst().getId());
        } else {
            button1.setText("+");
        }

        if (users.size() > 1) {
            button2.setText(users.get(1).getName());
            button2.setUserData(users.get(1).getId());
        } else {
            button2.setText("+");
        }

        if (users.size() > 2) {
            button3.setText(users.get(2).getName());
            button3.setUserData(users.get(2).getId());
        } else {
            button3.setText("+");
        }
    }

    @FXML
    private void handleButtonClick(MouseEvent event) {
        final Button clickedButton = (Button) event.getSource();
        if ("+".equals(clickedButton.getText())) {
            handleCreateProfile(event);
        } else {
            final int userId = (int) clickedButton.getUserData();
            final UserRepositoryImpl userRepository = new UserRepositoryImpl();
            final UserEntity selectedProfile = userRepository.findUserById(userId);
            UserContext.setCurrentUser(selectedProfile);
        }
    }
}
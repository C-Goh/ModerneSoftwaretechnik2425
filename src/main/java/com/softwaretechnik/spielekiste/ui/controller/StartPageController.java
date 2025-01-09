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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;

public class StartPageController {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Text profileName1;

    @FXML
    private Text profileName2;

    @FXML
    private Text profileName3;

    @FXML
    private void handleCreateProfile(MouseEvent event) {
        PageLoader.getInstance().loadCreateProfilePage(event);
    }

    @FXML
    public void initialize() {
        final UserRepositoryImpl userRepository = new UserRepositoryImpl();
        final List<UserEntity> users = userRepository.findAllUsers();

        if (!users.isEmpty()) {
            profileName1.setText(users.get(0).getName());
            button1.setUserData(users.get(0).getId());
        } else {
            profileName1.setText("+");
        }

        if (users.size() > 1) {
            profileName2.setText(users.get(1).getName());
            button2.setUserData(users.get(1).getId());
        } else {
            profileName2.setText("+");
        }

        if (users.size() > 2) {
            profileName3.setText(users.get(2).getName());
            button3.setUserData(users.get(2).getId());
        } else {
            profileName3.setText("+");
        }
    }
    @FXML
    private void handleButtonClick(MouseEvent event) {
        final Button clickedButton = (Button) event.getSource();
        if (clickedButton.getUserData() == null) {
            handleCreateProfile(event);
        } else {
            final int userId = (int) clickedButton.getUserData();
            final UserRepositoryImpl userRepository = new UserRepositoryImpl();
            final UserEntity selectedProfile = userRepository.findUserById(userId);
            UserContext.setCurrentUser(selectedProfile);
            PageLoader.getInstance().loadGameOverviewPage(event);
        }
    }

}
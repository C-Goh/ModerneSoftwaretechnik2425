package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StartPageController {

    private final UserRepository userRepository;

    // Spring injiziert automatisch eine Instanz von UserRepositoryImpl
    @Autowired
    public StartPageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
            stage.setScene(new Scene(root, 800 , 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        final List<UserEntity> users = userRepository.findAllUsers();

        if (!users.isEmpty()) {
            button1.setText(users.get(0).getName());
            button1.setUserData(users.get(0).getId());
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
            final UserEntity selectedProfile = userRepository.findUserById(userId);
            UserContext.setCurrentUser(selectedProfile);

            // Load GameOverview.fxml
            try {
                final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/GameOverview.fxml"));
                final Parent root = loader.load();
                final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 800 , 600));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
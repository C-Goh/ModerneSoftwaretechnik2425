package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import com.softwaretechnik.spielekiste.user.application.service.UserService;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.domain.service.UserDomainService;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class UpdateProfileController {

    @FXML
    private TextField profileNameField;

    public Button updateProfileButton;

    public Button deleteProfilButton;

    private UserService userService;

    public UpdateProfileController() {
        this.userService = new UserService(new UserRepositoryImpl(), new UserDomainService());
    }

    @FXML
    public void initialize() {
        UserEntity currentUser = UserContext.getCurrentUser();
        if (currentUser != null) {
            profileNameField.setText(currentUser.getName());
        }
    }

    @FXML
    private void updateProfile(MouseEvent event) {
        UserEntity currentUser = UserContext.getCurrentUser();
        if (currentUser != null) {
            currentUser.setName(profileNameField.getText());
            userService.updateUser(currentUser);
            PageLoader.getInstance().loadGameOverviewPage(event);
        }
    }

    @FXML
    private void deleteProfile(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Profil löschen");
        alert.setHeaderText("Profil löschen");
        alert.setContentText("Möchten Sie das Profil wirklich löschen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            UserEntity currentUser = UserContext.getCurrentUser();
            if (currentUser != null) {
                userService.deleteUser(currentUser.getId());
                UserContext.setCurrentUser(null);
                PageLoader.getInstance().loadStartPage(event);
            }
        }
    }

    @FXML
    private void loadGameOverview(MouseEvent event) {
        PageLoader.getInstance().loadGameOverviewPage(event);
    }
}
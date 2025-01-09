package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.List;

public class CreateProfileController {

    @FXML
    private TextField profileNameField;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Text pageTitle;

    @FXML
    private ImageView backButton;

    private final BooleanProperty hasProfiles = new SimpleBooleanProperty();

    @FXML
    public void initialize() {
        profileNameField.setText("");
        final UserRepositoryImpl userRepository = new UserRepositoryImpl();
        final List<UserEntity> users = userRepository.findAllUsers();
        hasProfiles.set(!users.isEmpty());
        backButton.visibleProperty().bind(hasProfiles);

        if (users.isEmpty()) {
            hasProfiles.set(true);
        }
    }

    @FXML
    private void createProfile(MouseEvent event) {
        try {
            final String name = profileNameField.getText();

            if (name == null || name.trim().isEmpty()) {
                System.out.println("Bitte gebe deinen Namen ein");
                return;
            }

            final UserEntity user = new UserEntity();
            user.setName(name);

            final UserRepositoryImpl userRepository = new UserRepositoryImpl();
            userRepository.createUser(user);

            PageLoader.getInstance().loadStartPage(event);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadStartPage(MouseEvent event) {
        PageLoader.getInstance().loadStartPage(event);
    }
}

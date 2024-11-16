package com.softwaretechnik.spielekiste;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.List;

public class App extends Application {
    private UserRepository userRepository;

    @Override
    public void start(Stage primaryStage) {
        SQLiteManager.initializeDatabase();
        userRepository = new UserRepositoryImpl();

        if (hasUsers()) {
            primaryStage.setScene(createUserListScene());
        } else {
            primaryStage.setScene(createUserInputScene(primaryStage));
        }

        primaryStage.setTitle("User Management");
        primaryStage.show();
    }

    private boolean hasUsers() {
        return !userRepository.findAllUsers().isEmpty();
    }

    private Scene createUserInputScene(Stage primaryStage) {
        final VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        final Label label = new Label("Enter your name:");
        final TextField nameField = new TextField();
        final Button submitButton = new Button("Create User");

        submitButton.setOnAction(event -> {
            final String name = nameField.getText();
            if (!name.isEmpty()) {
                userRepository.createUser(new UserEntity(0, name));
                primaryStage.setScene(createUserListScene());
            }
        });

        vbox.getChildren().addAll(label, nameField, submitButton);
        return new Scene(vbox, 300, 200);
    }

    private Scene createUserListScene() {
        final HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10));

        final List<UserEntity> users = userRepository.findAllUsers();
        for (UserEntity user : users) {
            final Label userLabel = new Label(user.getName());
            hbox.getChildren().add(userLabel);
        }

        return new Scene(hbox, 300, 200);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
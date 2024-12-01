package com.softwaretechnik.spielekiste;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        final UserRepositoryImpl userRepository = new UserRepositoryImpl();
        final List<UserEntity> users = userRepository.findAllUsers();

        final String fxmlFile = users.isEmpty() ? "/ui/view/CreateProfile.fxml" : "/ui/view/StartPage.fxml";
        final Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        primaryStage.setTitle(users.isEmpty() ? "Create Profile" : "Start Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        // initialize the database
        PropertyLoader.loadProperties("src/main/resources/application.properties");
        SQLiteManager.initializeDatabase();
        launch(args);
    }
}
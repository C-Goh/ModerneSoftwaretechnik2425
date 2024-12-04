package com.softwaretechnik.spielekiste;

import com.softwaretechnik.spielekiste.infrastructure.aspect.GamePointsAspect;
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
        // Initialize the repository and fetch users
        final UserRepositoryImpl userRepository = new UserRepositoryImpl();
        final List<UserEntity> users = userRepository.findAllUsers();

        final String fxmlFile = users.isEmpty() ? "/ui/view/CreateProfile.fxml" : "/ui/view/StartPage.fxml";
        final Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        primaryStage.setTitle(users.isEmpty() ? "Create Profile" : "Start Page");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Initialize the database and other services
        PropertyLoader.loadProperties("src/main/resources/application.properties");
        SQLiteManager.initializeDatabase();

        // Create GamePointsAspect and inject the UserRepository manually
        final UserRepositoryImpl userRepository = new UserRepositoryImpl();
        final GamePointsAspect gamePointsAspect = new GamePointsAspect();
        gamePointsAspect.setUserRepository(userRepository); // Manually inject the dependency

        // Ensure AspectJ weaving happens at runtime
        // This is a simplified way of ensuring that aspects are applied.
        // Normally, AspectJ weaving would need to happen through a build process, like using a plugin or load-time weaving.

        // Now you can launch the application
        launch(args);
    }
}
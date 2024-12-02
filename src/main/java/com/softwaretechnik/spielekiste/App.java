package com.softwaretechnik.spielekiste;

import com.softwaretechnik.spielekiste.config.AppConfig;
import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Initialize the repository and fetch users
        UserRepositoryImpl userRepository = context.getBean(UserRepositoryImpl.class);
        List<UserEntity> users = userRepository.findAllUsers();

        String fxmlFile = users.isEmpty() ? "/ui/view/CreateProfile.fxml" : "/ui/view/StartPage.fxml";
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        primaryStage.setTitle(users.isEmpty() ? "Create Profile" : "Start Page");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        context.close();
    }

    public static void main(String[] args) {
        // Initialize the database and other services
        PropertyLoader.loadProperties("src/main/resources/application.properties");
        SQLiteManager.initializeDatabase();

        // Launch the application
        launch(args);
    }
}
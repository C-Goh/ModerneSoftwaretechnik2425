package com.softwaretechnik.spielekiste.ui;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.ui.controller.CreateProfileController;
import com.softwaretechnik.spielekiste.user.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class CreateProfileControllerUITest extends ApplicationTest {

    private UserRepositoryImpl userRepository;

    @Override
    public void start(Stage stage) throws Exception {
        userRepository = new UserRepositoryImpl();

        PropertyLoader.loadProperties("src/test/resources/test-application.properties");
        SQLiteManager.initializeDatabase();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/CreateProfile.fxml"));
        Parent root = loader.load();
        CreateProfileController createProfileController = loader.getController();
        stage.setScene(new Scene(root, 800 , 600));
        stage.show();
    }


    @Test
    public void testCreateProfileButton() {
        // Given
        TextField profileNameField = lookup("#profileNameField").query();
        profileNameField.setText("TestUser");

        // When
        clickOn("#createProfileButton");

        // Verify that the scene has changed by checking an element from StartPage.fxml
        assert lookup("#button1").query() != null;

        userRepository.deleteUser(userRepository.findUserByName("TestUser").getId());
    }

    @Test
    public void testBackButton() {
        // When
        clickOn("#backButton");

        // Then
        // Add assertions to verify the expected behavior after clicking the back button
    }
}
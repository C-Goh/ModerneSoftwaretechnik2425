package com.softwaretechnik.spielekiste.ui;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.ui.controller.CreateProfileController;
import com.softwaretechnik.spielekiste.user.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void testCreateProfileButton() {
        // Given
        TextField profileNameField = lookup("#profileNameField").query();
        profileNameField.setText("TestUser");

        // When
        clickOn("#createProfileButton");
        WaitForAsyncUtils.waitForFxEvents();
        // Verify that the scene has changed by checking an element from StartPage.fxml
        assertNotNull(lookup("#button1").query());

        userRepository.deleteUser(userRepository.findUserByName("TestUser").getId());
    }

    @Test
    public void testBackButton() {
        // When
        clickOn("#backButton");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(lookup("#button1").query());
    }
}
package com.softwaretechnik.spielekiste.ui;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.ui.controller.StartPageController;
import com.softwaretechnik.spielekiste.user.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.testfx.util.WaitForAsyncUtils;

public class StartPageControllerUITest extends ApplicationTest {

    private UserRepositoryImpl userRepository;


    @Override
    public void start(Stage stage) throws Exception {
        userRepository = new UserRepositoryImpl();

        PropertyLoader.loadProperties("src/test/resources/test-application.properties");
        SQLiteManager.initializeDatabase();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/StartPage.fxml"));
        Parent root = loader.load();
        StartPageController startPageController = loader.getController();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void testInitialize() {
        Button button1 = lookup("#button1").query();
        Button button2 = lookup("#button2").query();
        Button button3 = lookup("#button3").query();

        assertNotNull(button1);
        assertNotNull(button2);
        assertNotNull(button3);

        // Verify button texts
        assertEquals("+", button1.getText());
        assertEquals("+", button2.getText());
        assertEquals("+", button3.getText());
    }

    @Test
    public void testHandleCreateProfile() {
        clickOn("#createProfileButton");
        WaitForAsyncUtils.waitForFxEvents();
        // Verify that the scene has changed by checking an element from CreateProfile.fxml
        assertNotNull(lookup("#backButton").query());
    }

    @Test
    public void testHandleButtonClick() {
        clickOn("#button1");
        WaitForAsyncUtils.waitForFxEvents();
        // Verify that the scene has changed by checking an element from GameOverview.fxml
        assertNotNull(lookup("#helloLabel").query());
    }
}
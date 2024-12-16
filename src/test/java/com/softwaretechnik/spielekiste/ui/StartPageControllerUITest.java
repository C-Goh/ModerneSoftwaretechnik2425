package com.softwaretechnik.spielekiste.ui;

import com.softwaretechnik.spielekiste.shared.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.ui.controller.StartPageController;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Text profileName1 = lookup("#profileName1").query();
        Text profileName2 = lookup("#profileName1").query();
        Text profileName3 = lookup("#profileName1").query();

        assertNotNull(profileName1);
        assertNotNull(profileName2);
        assertNotNull(profileName3);

        // Verify button texts
        assertEquals("+", profileName1.getText());
        assertEquals("+", profileName2.getText());
        assertEquals("+", profileName3.getText());
    }

    @Test
    public void testHandleButtonClick() {
        clickOn("#button1");
        // Verify that the scene has changed by checking an element from GameOverview.fxml
        assertNotNull(lookup("#profileNameField").query());
    }


    @Test
    public void testHandleCreateProfile() {
        // Simulate a mouse click on the button1
        clickOn("#button1");

        // Verify that the scene has changed by checking an element from CreateProfile.fxml
        assertNotNull(lookup("#profileNameField").query());
    }
}
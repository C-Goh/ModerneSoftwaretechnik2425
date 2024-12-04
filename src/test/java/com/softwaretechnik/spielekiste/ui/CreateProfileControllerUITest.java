package com.softwaretechnik.spielekiste.ui;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class CreateProfileControllerUITest {

    private UserRepositoryImpl userRepository;

    @Start
    public void start(Stage stage) throws Exception {
        userRepository = new UserRepositoryImpl();

        PropertyLoader.loadProperties("src/test/resources/test-application.properties");
        SQLiteManager.initializeDatabase();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/CreateProfile.fxml"));
        Parent root = loader.load();
        loader.getController();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    @Test
    public void testCreateProfileButton(FxRobot robot) {
        // Given
        TextField profileNameField = robot.lookup("#profileNameField").query();
        profileNameField.setText("TestUser");

        // When
        robot.clickOn("#createProfileButton");

        // Verify that the scene has changed by checking an element from StartPage.fxml
        Assertions.assertThat(robot.lookup("#button1").queryButton()).isVisible();

        userRepository.deleteUser(userRepository.findUserByName("TestUser").getId());
    }

    @Test
    @Disabled
    public void testBackButton(FxRobot robot) {
        // TODO Backbutton ist nicht sichtbar, vermutlich weil Szene nicht vom Startfenster gestartet wird.
        // When
        robot.clickOn("#backButton");
        Assertions.assertThat(robot.lookup("#button1").queryButton()).isVisible();
    }
}
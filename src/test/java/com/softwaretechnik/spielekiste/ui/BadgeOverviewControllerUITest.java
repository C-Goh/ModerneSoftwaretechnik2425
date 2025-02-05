package com.softwaretechnik.spielekiste.ui;

import com.softwaretechnik.spielekiste.shared.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class BadgeOverviewControllerUITest {

    @BeforeAll
    public static void setUpClass() {
        // Mock the UserContext to return a valid user
        UserEntity mockUser = new UserEntity();
        mockUser.setId(1); // Set a valid user ID
        mockUser.setName("TestUser");
        UserContext.setCurrentUser(mockUser);
    }

    @Start
    public void start(Stage stage) throws Exception {
        PropertyLoader.loadProperties("src/test/resources/test-application.properties");
        SQLiteManager.initializeDatabase();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/BadgeOverview.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    @Test
    public void testLoadGameOverviewPage(FxRobot robot) {
        // Simulate a mouse click on the backButton
        robot.clickOn("#backButton");

        // Verify that the scene has changed by checking an element from GameOverview.fxml
        Assertions.assertThat(robot.lookup("#helloLabel").queryLabeled()).isVisible();
    }
}
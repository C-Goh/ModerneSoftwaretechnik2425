package com.softwaretechnik.spielekiste.ui;

import com.softwaretechnik.spielekiste.shared.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class GameOverviewControllerUITest {

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/GameOverview.fxml"));
        Parent root = loader.load();
        loader.getController();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    @Test
    @Disabled
    public void testLoadQuizPage(FxRobot robot) {
        // Simulate a mouse click on the quizIcon
        robot.clickOn("#quizIcon");

        // Verify that the scene has changed by checking an element from Quiz.fxml
        Assertions.assertThat(robot.lookup("#questionLabel").queryLabeled()).isVisible();
    }

    @Test
    @Disabled
    public void testLoadBadgeOverviewPage(FxRobot robot) {
        // TODO: Implement this test
        // Simulate a mouse click on the badgeIcon
        robot.clickOn("#badgeIcon");

        // Verify that the scene has changed by checking an element from BadgeOverview.fxml
        Assertions.assertThat(robot.lookup("#backButton").queryButton()).isVisible();
    }

    @Test
    public void testLoadStartPage(FxRobot robot) {
        // Simulate a mouse click on the backButton
        robot.clickOn("#backButton");

        // Verify that the scene has changed by checking an element from StartPage.fxml
        Assertions.assertThat(robot.lookup("#button1").queryButton()).isVisible();
    }

    @Test
    public void testHelloLabel(FxRobot robot) {
        // Verify that the helloLabel displays the correct text
        Label helloLabel = robot.lookup("#helloLabel").queryAs(Label.class);
        Assertions.assertThat(helloLabel).hasText("hello TestUser");
    }
}
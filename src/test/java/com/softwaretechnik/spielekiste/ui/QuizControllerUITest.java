package com.softwaretechnik.spielekiste.ui;

import com.softwaretechnik.spielekiste.shared.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.application.service.UserContext;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class QuizControllerUITest {

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/Quiz.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    @Test
    public void testStartQuiz(FxRobot robot) {
        // Verify that the first question is displayed
        Assertions.assertThat(robot.lookup("#questionLabel").queryLabeled()).hasText("What is 2+2?");
    }

    @Test
    public void testAnswerQuestion(FxRobot robot) {
        // Simulate clicking the first answer button
        robot.clickOn("#answerButton1");

        // Verify that the next question is displayed
        Assertions.assertThat(robot.lookup("#questionLabel").queryLabeled()).hasText("What is the capital of France?");
    }

    @Test
    public void testCompleteQuiz(FxRobot robot) {
        // Simulate answering all questions
        robot.clickOn("#answerButton1");
        robot.clickOn("#answerButton2");
        robot.clickOn("#answerButton3");
        robot.clickOn("#answerButton4");

        // Verify that the quiz completion message is displayed
        Assertions.assertThat(robot.lookup("#questionLabel").queryLabeled()).hasText("Quiz completed!");
    }

    @Test
    public void testLoadGameOverview(FxRobot robot) {
        robot.clickOn("#backButton");

        // Verify that the scene has changed by checking an element from GameOverview.fxml
        Assertions.assertThat(robot.lookup("#profileImageView").queryAs(ImageView.class)).isVisible();
    }
}
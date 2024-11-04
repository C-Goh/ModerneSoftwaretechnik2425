package softwaretechnik;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest extends ApplicationTest {

    @Test
    public void testLabel() {
        Platform.runLater(() -> {
            Label label = new Label("Hello, JavaFX!");
            assertEquals("Hello, JavaFX!", label.getText());
        });
    }
}
package com.softwaretechnik.spielekiste;

import com.softwaretechnik.spielekiste.App;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.assertions.api.Assertions.assertThat;

public class AppUiTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        new App().start(stage);
    }

    @Test
    public void shouldContainLabelWithText() {
        Label label = lookup(".label").query();
    }
}
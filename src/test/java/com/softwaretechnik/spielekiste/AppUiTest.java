package com.softwaretechnik.spielekiste;

import com.softwaretechnik.spielekiste.App;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.assertions.api.Assertions.assertThat;

public class AppUiTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
    }

    @Test
    public void shouldContainPageTitle() {
        Text pageTitle = lookup("#pageTitle").query();
        assertThat(pageTitle).hasText("Was ist dein Profilname?");
    }
}
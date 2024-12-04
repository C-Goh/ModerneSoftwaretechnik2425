package com.softwaretechnik.spielekiste;

import com.softwaretechnik.spielekiste.App;
import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.infrastructure.config.PropertyLoader;
import com.softwaretechnik.spielekiste.user.infrastructure.persistence.UserRepositoryImpl;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.sql.SQLException;

import static org.testfx.assertions.api.Assertions.assertThat;

public class AppUiTest extends ApplicationTest {

    @BeforeEach
    public void setUp() throws SQLException {
        PropertyLoader.loadProperties("src/test/resources/test-application.properties");

        SQLiteManager.initializeDatabase();
    }

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
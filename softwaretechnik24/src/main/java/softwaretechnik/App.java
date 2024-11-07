package softwaretechnik;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import softwaretechnik.database.SQLiteManager;
import softwaretechnik.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        SQLiteManager.initializeDatabase();

        if (hasUsers()) {
            primaryStage.setScene(createUserListScene());
        } else {
            primaryStage.setScene(createUserInputScene(primaryStage));
        }

        primaryStage.setTitle("User Management");
        primaryStage.show();
    }

    private boolean hasUsers() {
        try (Connection connection = SQLiteManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM users");
            if (resultSet.next()) {
                return resultSet.getInt("count") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Scene createUserInputScene(Stage primaryStage) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label label = new Label("Enter your name:");
        TextField nameField = new TextField();
        Button submitButton = new Button("Create User");

        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                User.createUser(name);
                primaryStage.setScene(createUserListScene());
            }
        });

        vbox.getChildren().addAll(label, nameField, submitButton);
        return new Scene(vbox, 300, 200);
    }

    private Scene createUserListScene() {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10));

        List<String> userNames = getUserNames();
        for (String userName : userNames) {
            Label userLabel = new Label(userName);
            hbox.getChildren().add(userLabel);
        }

        return new Scene(hbox, 300, 200);
    }

    private List<String> getUserNames() {
        List<String> userNames = new ArrayList<>();
        try (Connection connection = SQLiteManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT name FROM users");
            while (resultSet.next()) {
                userNames.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userNames;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
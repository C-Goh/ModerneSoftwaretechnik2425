package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreateProfile {

    @FXML
    protected TextField nameField;

    @FXML
    protected Button createProfileButton;

    @FXML
    protected ImageView profileImageView;

    @FXML
    protected Button backButton;

    /*// Methode, die beim Klick auf den Button "Profil erstellen" ausgeführt wird
    @FXML
    void createProfile(MouseEvent event) {
        final String name = nameField.getText();

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Bitte gebe deinen Namen ein");
            return;
        }

        // Verbindung zur Datenbank über SQLiteManager aufbauen
        try (Connection connection = SQLiteManager.getConnection()) {
            final String sql = "INSERT INTO users (name) VALUES (?)";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            final int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Dein Profil wurde erfolgreich erstellt!");
                openGameOverview(); // Wechselt zur GameOverview.fxml
            }
        } catch (Exception e) {
            System.out.println("Oh nein - es gab einen Fehler beim Erstellen deines Profils: " + e.getMessage());
            e.printStackTrace();
        }
    }*/

    /*// Methode zum Laden und Öffnen der GameOverview.fxml
    private void openGameOverview() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameOverview.fxml"));
            Scene scene = new Scene(loader.load());

            // Holen der aktuellen Stage und Wechsel der Szene
            Stage stage = (Stage) createProfileButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Spieleübersicht" + e.getMessage());
            e.printStackTrace();
        }
    }*/

@FXML
    void showGameOverview(MouseEvent event) {
        System.out.println("Zeige die Spieleübersicht");

        try {
            // Lade die GameOverview.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/GameOverview.fxml"));
            Scene scene = new Scene(loader.load());

            // Holen der aktuellen Stage und Wechsel der Szene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Spieleübersicht: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Methode zum Navigieren zurück zur Startseite
    @FXML
    void loadStartPage(MouseEvent event) {
        System.out.println("Zurück zur Startseite");

        try {
            // Lade die Startseite (z. B. die Startseite.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/StartPage.fxml"));
            Scene scene = new Scene(loader.load());

            // Holen der aktuellen Stage und Wechsel der Szene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Startseite: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Methode zum Auswählen eines Profilbilds
    @FXML
    void handleProfilePictureClick(MouseEvent event) {
        System.out.println("Profilbild auswählen");
        // Hier Logik eingebauen, die es dem Benutzer erlaubt, ein Bild aus einem Datei-Dialog auszuwählen
    }
}

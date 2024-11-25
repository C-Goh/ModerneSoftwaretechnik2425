package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreateProfile {

    @FXML
    private TextField nameField;

    @FXML
    private Button createProfileButton;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Button backButton;

    // Methode, die beim Klick auf den Button "Profil erstellen" ausgeführt wird
    @FXML
    void handleCreateProfile(javafx.event.ActionEvent event) {
        String name = nameField.getText();

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Bitte gebe deinen Namen ein");
            return;
        }

        // Verbindung zur Datenbank über SQLiteManager aufbauen
        try (Connection connection = SQLiteManager.getConnection()) {
            String sql = "INSERT INTO users (name) VALUES (?)";  // SQL-Abfrage zum Hinzufügen des Profils
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Dein Profil wurde erfolgreich erstellt!");
            }
        } catch (Exception e) {
            System.out.println("Oh nein - es gab einen Fehler beim Erstellen deines Profils: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Methode zum Navigieren zurück zur Startseite
    @FXML
    void loadStartPage(MouseEvent event) {
        System.out.println("Zurück zur Startseite");
        // Hier Logik zum Navigieren zur Startseite einbauen
    }

    // Methode zum Auswählen eines Profilbilds
    @FXML
    void handleProfilePictureClick(MouseEvent event) {
        System.out.println("Profilbild auswählen");
        // Hier Logik eingebauen, die es dem Benutzer erlaubt, ein Bild aus einem Datei-Dialog auszuwählen
    }
}
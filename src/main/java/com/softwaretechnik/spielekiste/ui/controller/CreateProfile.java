package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreateProfile {

    @FXML
    private TextField nameField;

    @FXML
    private Button createProfileButton;

    // Methode, die beim Klick auf den Button ausgeführt wird
    @FXML
    void handleCreateProfile(javafx.event.ActionEvent event) {
        String name = nameField.getText();

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Bitte gebe deinen Namen ein");
            return;
        }

        // Verbindung zur Datenbank über SQLiteManager aufbauen
        try (Connection connection = SQLiteManager.getConnection()) {
            String sql = "INSERT INTO users (name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Dein Profil wurde erstellt!");
            }
        } catch (Exception e) {
            System.out.println("Oh nein - es hab einen Fehler beim Erstellen deines Profils: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


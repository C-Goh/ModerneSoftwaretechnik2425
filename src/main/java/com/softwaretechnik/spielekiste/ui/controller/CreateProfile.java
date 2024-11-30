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

    // Methode zum Laden und Öffnen der GameOverview.fxml
    @FXML
    void showGameOverview(MouseEvent event) {
        System.out.println("Zeige die Spieleübersicht");

        try {
            // Lade die GameOverview.fxml
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/GameOverview.fxml"));
            final Scene scene = new Scene(loader.load());

            // Holen der aktuellen Stage und Wechsel der Szene
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/StartPage.fxml"));
            final Scene scene = new Scene(loader.load());

            // Holen der aktuellen Stage und Wechsel der Szene
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        // Hier Logik einbauen, die es dem Benutzer erlaubt, ein Bild aus einem Datei-Dialog auszuwählen
    }
}

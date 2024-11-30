package com.softwaretechnik.spielekiste.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverview {

    // FXML-Elemente, die mit der FXML-Datei verbunden sind
    @FXML
    protected ImageView profileImageView;

    @FXML
    protected ImageView badgeIcon;

    @FXML
    protected ImageView backButton;

    // Methode, die aufgerufen wird, wenn das Abzeichen geklickt wird
    @FXML
    protected void loadAchievementsPage(ActionEvent event) {
        System.out.println("Abzeichen-Bild wurde geklickt. Lade Erfolge-Seite.");
        // Hier Logik für das Wechseln zu der Erfolge-Seite implementieren.
    }

    @FXML
    protected void loadStartPage(ActionEvent event) {
        System.out.println("Zurück-Button wurde geklickt. Lade Startseite.");
        try {
            // Lade die StartPage.fxml
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/StartPage.fxml"));
            final Parent root = loader.load();

            // Wechsle zur Startseite
            final Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Startseite");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Laden der StartPage.fxml.");
        }
    }
}

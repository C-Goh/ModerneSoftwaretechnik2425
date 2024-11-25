package com.softwaretechnik.spielekiste.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class GameOverview {

    // FXML-Elemente, die mit der FXML-Datei verbunden sind
    @FXML
    private ImageView profileImageView;

    @FXML
    private ImageView badgeIcon;

    @FXML
    private ImageView backButton;

    // Methode, die aufgerufen wird, wenn das Abzeichen geklickt wird
    @FXML
    private void loadAchievementsPage(ActionEvent event) {
        System.out.println("Abzeichen-Bild wurde geklickt. Lade Erfolge-Seite.");
        // Hier Logik für das Wechseln zu der Erfolge-Seite implementieren.
    }

    // Methode, die aufgerufen wird, wenn der Zurück-Button geklickt wird
    @FXML
    private void loadStartPage(ActionEvent event) {
        System.out.println("Zurück-Button wurde geklickt. Lade Startseite.");
        // HierLogik für das Wechseln zur Startseite implementieren.
    }
}

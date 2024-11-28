package com.softwaretechnik.spielekiste.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class StartPage {

    // FXML-Elemente, die aus der FXML-Datei geladen werden
    @FXML
    private Text pageTitle;

    @FXML
    private TextField profilNameField;

    @FXML
    private Button startButton;

    @FXML
    private Button createProfileButton;

    // Diese Methode wird aufgerufen, wenn der "Starten"-Button geklickt wird
    @FXML
    private void handleStartButtonClick(ActionEvent event) {
        final String profilName = profilNameField.getText();

        // Hier Logik hinzufügen, die den Profilnamen weiter verarbeitet
        System.out.println("Profilname: " + profilName);

        // Beispiel: Start-Button Aktion (hier eine einfache Konsolenausgabe)
        System.out.println("Start-Button wurde geklickt!");

        // loadNewScene();
    }

    // Diese Methode wird aufgerufen, wenn der "Ich habe noch kein Profil"-Button geklickt wird
    @FXML
    private void handleCreateProfile(ActionEvent event) {
        System.out.println("Create Profile Button clicked!");

        // loadCreateProfileScene();
    }

    // Beispiel einer Methode, um eine neue Szene zu laden (wenn gewünscht)
    private void loadNewScene() {
        // Logik zum Laden einer neuen Szene hier
        System.out.println("Neue Szene wird geladen...");
    }

    private void loadCreateProfileScene() {
        // Logik zum Erstellen eines Profils hier
        System.out.println("Erstelle ein neues Profil...");
    }
}
package com.softwaretechnik.spielekiste.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartPage {

    @FXML
    protected void onProfilClick(ActionEvent event) {
        // Stelle sicher, dass das Event von einer Schaltfläche ausgelöst wurde
        if (!(event.getSource() instanceof Button)) {
            System.err.println("Das Event wurde nicht von einer Schaltfläche ausgelöst.");
            return;
        }

        // Identifiziere die geklickte Schaltfläche
        final Button clickedButton = (Button) event.getSource();
        final String profilName = getProfilNameByButtonId(clickedButton.getId());

        if (profilName.isEmpty()) {
            System.err.println("Unbekannte Button-ID: " + clickedButton.getId());
            return;
        }

        // Lade die FXML-Datei und öffne die neue Szene
        loadCreateProfileScene(profilName, event);
    }

    public String getProfilNameByButtonId(String buttonId) {
        switch (buttonId) {
            case "buttonProfil1":
                return "Profil 1";
            case "buttonProfil2":
                return "Profil 2";
            case "buttonProfil3":
                return "Profil 3";
            default:
                return ""; // Unbekannte ID
        }
    }

    protected void loadCreateProfileScene(String profilName, ActionEvent event) {
        try {
            // Lade die FXML-Datei
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/CreateProfile.fxml"));
            final Parent root = loader.load();

            // Hole den aktuellen Stage und aktualisiere die Szene
            final Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Profil erstellen - " + profilName);
            stage.show();
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der CreateProfile-Szene: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
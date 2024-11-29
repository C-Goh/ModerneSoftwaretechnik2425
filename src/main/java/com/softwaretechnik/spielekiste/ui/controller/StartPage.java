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
    private void onProfilClick(ActionEvent event) {
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

    /**
     * Bestimmt den Profilnamen basierend auf der ID der Schaltfläche.
     *
     * @param buttonId die ID der geklickten Schaltfläche
     * @return der Profilname, oder ein leerer String, wenn die ID unbekannt ist
     */
    private String getProfilNameByButtonId(String buttonId) {
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

    /**
     * Lädt die `CreateProfile.fxml`-Datei und zeigt sie in der aktuellen Bühne an.
     *
     * @param profilName der Name des Profils, das erstellt wird
     * @param event      das auslösende Event
     */
    private void loadCreateProfileScene(String profilName, ActionEvent event) {
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
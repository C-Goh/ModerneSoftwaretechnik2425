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
        try {
            // Casten des Events, um die auslösende Schaltfläche zu identifizieren
            Button clickedButton = (Button) event.getSource();

            String profilName = ""; // Profilname, falls benötigt
            switch (clickedButton.getId()) {
                case "buttonProfil1":
                    profilName = "Profil 1";
                    break;
                case "buttonProfil2":
                    profilName = "Profil 2";
                    break;
                case "buttonProfil3":
                    profilName = "Profil 3";
                    break;
                default:
                    System.out.println("Unbekannter Button geklickt");
                    return; // Abbrechen, wenn die ID unbekannt ist
            }

            // Lade die CreateProfile.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/CreateProfile.fxml"));
            Parent root = loader.load();

            // Hole den Controller von CreateProfile.fxml
            //CreateProfile controller = loader.getController();

            // Übergebe den Profilnamen an den Controller
            // controller.setProfilName(profilName);

            // Öffne die CreateProfile.fxml in demselben Fenster
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Profil erstellen - " + profilName);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
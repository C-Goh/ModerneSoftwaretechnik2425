package com.softwaretechnik.spielekiste.ui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.Event;

public class PageLoader {

    // Singleton-Instanz (optional, wenn zentraler Zugriff gewünscht ist)
    private static PageLoader instance;

    private PageLoader() {
        // Verhindert externe Instanziierung
    }

    public static PageLoader getInstance() {
        if (instance == null) {
            instance = new PageLoader();
        }
        return instance;
    }

    public void loadStartPage(MouseEvent event) {
        try {
            // Lade die StartPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/StartPage.fxml"));
            Parent root = loader.load();

            // Aktuelle Bühne ermitteln
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Neue Szene setzen
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadQuizPage(MouseEvent event) {
        try {
            // Lade die Quiz-Seite
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/Quiz.fxml"));
            final Parent root = loader.load();

            // Aktuelle Bühne ermitteln
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Neue Szene setzen
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadBadgeOverviewPage(MouseEvent event) {
        try {
            // Lade die BadgeOverview-Seite (BadgeOverview.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/BadgeOverview.fxml"));
            Parent root = loader.load();

            // Aktuelle Bühne ermitteln
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Neue Szene setzen
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadGameOverviewPage(Event event) {
        try {
            // Lade die GameOverview-Seite (GameOverview.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/GameOverview.fxml"));
            Parent root = loader.load();

            // Aktuelle Bühne ermitteln
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Neue Szene setzen
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
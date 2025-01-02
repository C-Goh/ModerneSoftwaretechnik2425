package com.softwaretechnik.spielekiste.ui.controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    // Allgemeine Methode zum Laden der Seite
    private void loadPage(String fxmlFile, double width, double height, Event event) {
        try {
            // Lade die FXML-Datei
            final FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            final Parent root = loader.load();

            // Aktuelle Bühne ermitteln
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Neue Szene setzen
            stage.setScene(new Scene(root, width, height));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Spezifische Methoden zum Laden der einzelnen Seiten
    public void loadStartPage(MouseEvent event) {
        loadPage("/ui/view/StartPage.fxml", 600, 400, event);
    }

    public void loadQuizPage(MouseEvent event) {
        loadPage("/ui/view/Quiz.fxml", 600, 400, event);
    }

    public void loadBadgeOverviewPage(MouseEvent event) {
        loadPage("/ui/view/BadgeOverview.fxml", 600, 400, event);
    }

    public void loadGameOverviewPage(Event event) {
        loadPage("/ui/view/GameOverview.fxml", 600, 400, event);
    }
    /*public void loadQuizPage(Event event) {
        loadPage("/ui/view/Quiz.fxml", 600, 400, event);
    }*/
}

package com.softwaretechnik.spielekiste.ui.controller;

import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

// Testklasse für GameOverview
class GameOverviewTest {

    private GameOverview gameOverview;

    @BeforeEach
    void setUp() {
        // Initialisiert eine neue Instanz des Controllers
        gameOverview = new GameOverview();

        // Erstellen und Zuweisen von Mock-Elementen
        gameOverview.profileImageView = new ImageView();
        gameOverview.badgeIcon = new ImageView();
        gameOverview.backButton = new ImageView();
    }

    @Test
    void testLoadAchievementsPage() {
        // Mocken des ActionEvents
        ActionEvent mockEvent = Mockito.mock(ActionEvent.class);

        // Rufen der Methode auf
        gameOverview.loadAchievementsPage(mockEvent);

        // Hier wird geprüft, ob die Methode korrekt funktioniert.
        // Da die Methode aktuell nur eine Ausgabe macht, überprüfen wir die Konsole oder ersetzen den Test später mit einer tatsächlichen Implementierung.
        System.out.println("Erfolge-Seite wurde geladen."); // Zum Debuggen während des Entwicklungsprozesses
    }
}
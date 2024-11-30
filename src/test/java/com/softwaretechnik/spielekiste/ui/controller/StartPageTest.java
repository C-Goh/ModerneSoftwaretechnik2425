package com.softwaretechnik.spielekiste.ui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StartPageTest {

    private StartPage startPage;

    @BeforeEach
    void setUp() {
        // Vor jedem Test wird ein neuer StartPage-Controller erstellt.
        startPage = new StartPage();
    }

    @Test
    void testGetProfilNameByButtonId() {
        // Testet die Zuordnung von Button-IDs zu Profilnamen.
        assertEquals("Profil 1", startPage.getProfilNameByButtonId("buttonProfil1"));
        assertEquals("Profil 2", startPage.getProfilNameByButtonId("buttonProfil2"));
        assertEquals("Profil 3", startPage.getProfilNameByButtonId("buttonProfil3"));
        assertEquals("", startPage.getProfilNameByButtonId("unknownButtonId")); // Unbekannte ID
    }

    /*@Test
    void testOnProfilClickWithValidButton() {
        // Simuliert ein ActionEvent, das von einem Button ausgelöst wurde
        ActionEvent mockEvent = mock(ActionEvent.class);

        // Erstellt einen Button mit einer gültigen ID
        Button mockButton = new Button();
        mockButton.setId("buttonProfil1");
        when(mockEvent.getSource()).thenReturn(mockButton);

        // Erstellt einen Spy für den StartPage-Controller, um `loadCreateProfileScene` zu überwachen
        StartPage spyStartPage = Mockito.spy(startPage);
        doNothing().when(spyStartPage).loadCreateProfileScene(anyString(), eq(mockEvent)); // Methode wird simuliert

        // Ruft die Methode `onProfilClick` auf
        spyStartPage.onProfilClick(mockEvent);

        // Überprüft, dass die Methode `loadCreateProfileScene` mit den richtigen Parametern aufgerufen wurde
        verify(spyStartPage, times(1)).loadCreateProfileScene(eq("Profil 1"), eq(mockEvent));
    }*/

    /*@Test
    void testOnProfilClickWithInvalidButtonId() {
        // Simuliert ein ActionEvent von einem Button mit einer ungültigen ID
        ActionEvent mockEvent = mock(ActionEvent.class);

        Button mockButton = new Button();
        mockButton.setId("unknownButtonId");
        when(mockEvent.getSource()).thenReturn(mockButton);

        // Erstellt einen Spy für den StartPage-Controller
        StartPage spyStartPage = Mockito.spy(startPage);

        // Ruft die Methode `onProfilClick` auf
        spyStartPage.onProfilClick(mockEvent);

        // Überprüft, dass `loadCreateProfileScene` NICHT aufgerufen wurde
        verify(spyStartPage, times(0)).loadCreateProfileScene(anyString(), any(ActionEvent.class));
    }*/

    /*@Test
    void testOnProfilClickWithInvalidSource() {
        // Simuliert ein ActionEvent, dessen Quelle KEIN Button ist
        ActionEvent mockEvent = mock(ActionEvent.class);
        when(mockEvent.getSource()).thenReturn(new Object()); // Quelle ist kein Button

        // Erstellt einen Spy für den StartPage-Controller
        StartPage spyStartPage = Mockito.spy(startPage);

        // Ruft die Methode `onProfilClick` auf
        spyStartPage.onProfilClick(mockEvent);

        // Überprüft, dass `loadCreateProfileScene` NICHT aufgerufen wurde
        verify(spyStartPage, times(0)).loadCreateProfileScene(anyString(), any(ActionEvent.class));
    }*/
}


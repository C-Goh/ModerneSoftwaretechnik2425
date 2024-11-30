package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class CreateProfileTest {

    private CreateProfile createProfile;

    @BeforeEach
    void setUp() {
        // Initialisiert den Controller
        createProfile = new CreateProfile();

        // Mocke die FXML-Elemente
        createProfile.nameField = mock(javafx.scene.control.TextField.class);
        createProfile.createProfileButton = mock(javafx.scene.control.Button.class);
        createProfile.profileImageView = mock(javafx.scene.image.ImageView.class);
        createProfile.backButton = mock(javafx.scene.control.Button.class);
    }

    /*@Test
    void testCreateProfile_NameFieldEmpty() {
        // Simuliere ein leeres Textfeld
        when(createProfile.nameField.getText()).thenReturn("");

        // Simuliere einen Mausklick
        MouseEvent mockEvent = Mockito.mock(MouseEvent.class);

        // Rufe die Methode auf
        createProfile.createProfile(mockEvent);

        // Überprüfe die Konsolenausgabe oder einfach, dass keine weiteren Methoden aufgerufen werden
        try {
            verifyNoInteractions(SQLiteManager.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    /*@Test
    void testCreateProfile_NameFieldValid() throws Exception {
        // Simuliere einen gültigen Namen
        when(createProfile.nameField.getText()).thenReturn("TestUser");

        // Simuliere einen Mausklick
        MouseEvent mockEvent = Mockito.mock(MouseEvent.class);

        // Mocke die SQLiteManager-Datenbankoperationen
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        when(SQLiteManager.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        // Rufe die Methode auf
        createProfile.createProfile(mockEvent);

        // Verifiziere, dass die SQL-Operationen korrekt ausgeführt wurden
        verify(mockConnection).prepareStatement("INSERT INTO users (name) VALUES (?)");
        verify(mockStatement).setString(1, "TestUser");
        verify(mockStatement).executeUpdate();
    }*/

    /*@Test
    void testCreateProfile_DatabaseError() throws Exception {
        // Simuliere einen gültigen Namen
        when(createProfile.nameField.getText()).thenReturn("TestUser");

        // Simuliere einen Mausklick
        MouseEvent mockEvent = Mockito.mock(MouseEvent.class);

        // Mocke, dass der SQLiteManager eine Exception auslöst
        when(SQLiteManager.getConnection()).thenThrow(new RuntimeException("Datenbankfehler"));

        // Rufe die Methode auf
        createProfile.createProfile(mockEvent);

        // Überprüfe, dass die Exception korrekt behandelt wird
        // Hier könnten keine SQL-Operationen erfolgreich ausgeführt werden
    }*/

    /*@Test
    void testLoadStartPage() {
        // Simuliere einen Mausklick
        MouseEvent mockEvent = Mockito.mock(MouseEvent.class);

        // Rufe die Methode auf
        createProfile.loadStartPage(mockEvent);

        // Überprüfe, dass die Methode ohne Fehler ausgeführt wird
        // Hier könnte man die Konsolenausgabe mit einem Logger überprüfen, falls erforderlich
    }*/

    /*@Test
    void testHandleProfilePictureClick() {
        // Simuliere einen Mausklick
        MouseEvent mockEvent = Mockito.mock(MouseEvent.class);

        // Rufe die Methode auf
        createProfile.handleProfilePictureClick(mockEvent);

        // Überprüfe, dass die Methode ohne Fehler ausgeführt wird
        // Da diese Methode noch nicht implementiert ist, gibt es keinen weiteren Testinhalt
    }*/
}


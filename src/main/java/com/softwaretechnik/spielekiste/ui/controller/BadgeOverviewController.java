package com.softwaretechnik.spielekiste.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BadgeOverviewController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBoxContent;

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Text pageTitle;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        // Setze Eigenschaften für ScrollPane und VBox
        scrollPane.setFitToWidth(true);
        vBoxContent.setSpacing(10); // Passt den Abstand zwischen den HBox-Elementen an

        // Füge die Items zur ChoiceBox hinzu
        categoryChoiceBox.getItems().addAll("Alle", "Quiz", "TicTacToe", "Taschenrechner", "QuickKlick");

        // Setze einen Standardwert für die ChoiceBox
        categoryChoiceBox.setValue("Alle");

        // anchorPane.setMinWidth(600);  // Minimale Breite auf 400 Pixel setzen
        // anchorPane.setMinHeight(400); // Minimale Höhe auf 600 Pixel setzen
    }

    @FXML
    private void loadGameOverview(MouseEvent event) {
        PageLoader.getInstance().loadGameOverviewPage(event);
    }
}

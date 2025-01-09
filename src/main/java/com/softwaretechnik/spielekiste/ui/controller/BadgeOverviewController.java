package com.softwaretechnik.spielekiste.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
        scrollPane.setFitToWidth(true);
        vBoxContent.setSpacing(10);
        categoryChoiceBox.getItems().addAll("Alle", "Quiz", "TicTacToe", "Taschenrechner", "QuickKlick");
        categoryChoiceBox.setValue("Alle");
    }

    @FXML
    private void loadGameOverview(MouseEvent event) {
        PageLoader.getInstance().loadGameOverviewPage(event);
    }
}

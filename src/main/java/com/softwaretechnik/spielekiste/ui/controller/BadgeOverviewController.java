package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;
import com.softwaretechnik.spielekiste.badge.domain.repository.BadgeRepository;
import com.softwaretechnik.spielekiste.badge.infrastructure.persistence.BadgeRepositoryImpl;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.stream.Collectors;

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

    private BadgeRepository badgeRepository = new BadgeRepositoryImpl();

    @FXML
    public void initialize() {
        scrollPane.setFitToWidth(true);
        vBoxContent.setSpacing(10);
        categoryChoiceBox.getItems().addAll("Alle", "Quiz", "TicTacToe", "Taschenrechner", "QuickKlick");
        categoryChoiceBox.setValue("Alle");

        categoryChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> filterBadges(newValue));

        loadBadges();
    }

    private void loadBadges() {
        List<BadgeEntity> badges = badgeRepository.findAll();
        displayBadges(badges, "Alle");
    }

    private void filterBadges(String category) {
        List<BadgeEntity> badges = badgeRepository.findAll();
        if (!"Alle".equals(category)) {
            badges = badges.stream()
                    .filter(badge -> badge.getGameType().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }
        displayBadges(badges, category);
    }

    private void displayBadges(List<BadgeEntity> badges, String category) {
        vBoxContent.getChildren().clear();
        if (badges.isEmpty()) {
            Text noBadgesText = new Text("Du hast in dem Spiel " + category + " noch keine Abzeichen verdient.");
            noBadgesText.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
            vBoxContent.getChildren().add(noBadgesText);
        } else {
            for (BadgeEntity badge : badges) {
                HBox hBox = createBadgeHBox(badge);
                vBoxContent.getChildren().add(hBox);
            }
        }
    }

    private HBox createBadgeHBox(BadgeEntity badge) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(200.0);
        hBox.setPrefWidth(595.0);

        ImageView imageView = new ImageView("/Bildmaterial/Abzeichen.png");
        imageView.setFitHeight(200.0);
        imageView.setFitWidth(200.0);
        imageView.setOpacity(0.5);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefHeight(200.0);
        textArea.setPrefWidth(350.0);
        textArea.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: transparent; -fx-font-size: 16px;");
        textArea.setText("\n\n\n" + badge.getName() + "\n\n" + badge.getText());

        hBox.getChildren().addAll(imageView, textArea);
        return hBox;
    }

    @FXML
    private void loadGameOverview(MouseEvent event) {
        PageLoader.getInstance().loadGameOverviewPage(event);
    }
}
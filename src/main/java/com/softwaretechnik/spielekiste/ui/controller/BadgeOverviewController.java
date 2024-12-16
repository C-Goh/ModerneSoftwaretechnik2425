package com.softwaretechnik.spielekiste.ui.controller;

import com.softwaretechnik.spielekiste.badge.application.service.BadgeService;
import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;

public class BadgeOverviewController {

    private BadgeService badgeService;

    public void badgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    public void showBadges() {
        final List<BadgeEntity> badges = badgeService.getAllBadges();
        for (BadgeEntity badge : badges) {
            if (badge.getHasEarned()) {
                System.out.println("You have earned the badge: " + badge.getName());
            } else {
                System.out.println("You have not earned the badge: " + badge.getName());
            }
        }
    }

    @FXML
    private void loadGameOverviewPage(MouseEvent event) {
        try {
            // Load GameOverview.fxml
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/GameOverview.fxml"));
            final Parent root = loader.load();
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

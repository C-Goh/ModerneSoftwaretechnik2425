package com.softwaretechnik.spielekiste.ui.controller;

import java.util.List;

import com.softwaretechnik.spielekiste.badge.application.service.BadgeService;
import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;

public class BadgeOverviewController {
    
    private BadgeService badgeService;

    public void BadgeOverviewController(BadgeService badgeService) {
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
      
}

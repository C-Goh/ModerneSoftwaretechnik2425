package com.softwaretechnik.spielekiste.badge.domain.service;

import com.softwaretechnik.spielekiste.game.domain.Game;

import java.util.List;

public class BadgeDomainService<T> {
    private final List<BadgeCondition<T>> badgeConditions;

    public BadgeDomainService(List<BadgeCondition<T>> badgeConditions) {
        this.badgeConditions = badgeConditions;
    }

    public void checkAndAwardBadges(Game game) {
        System.out.println("checkAndAwardBadges called with game: " + game);
        System.out.println("Badge conditions size: " + badgeConditions.size());

        for (BadgeCondition<T> condition : badgeConditions) {
            System.out.println("Checking condition: " + condition.getBadgeId());
            if (condition.isConditionMet(game)) {
                System.out.println("Awarding badge: " + condition.getBadgeId() + " to user: " + game.getUser().getId() + " for game type: " + game.getGameType());
                condition.awardBadge(game.getUser(), condition.getBadgeId(), game.getGameType());
            } else {
                System.out.println("Condition not met for badge: " + condition.getBadgeId());
            }
        }
    }
}
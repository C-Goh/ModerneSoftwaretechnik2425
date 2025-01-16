package com.softwaretechnik.spielekiste.badge.domain.service;

import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;
import com.softwaretechnik.spielekiste.game.domain.Game;

import java.util.List;

public class BadgeDomainService<T> {
    private final List<BadgeCondition<T>> badgeConditions;

    public BadgeDomainService(List<BadgeCondition<T>> badgeConditions) {
        this.badgeConditions = badgeConditions;
    }

    public void checkAndAwardBadges(Game game) {
        for (BadgeCondition<T> condition : badgeConditions) {
            if (condition.isConditionMet(game)) {
                condition.awardBadge(game.getUser(), condition.getBadgeId(), game.getGameType());
            }
        }
    }
}
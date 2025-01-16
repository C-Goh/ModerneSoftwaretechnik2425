package com.softwaretechnik.spielekiste.badge.domain.service;

import com.softwaretechnik.spielekiste.game.domain.Game;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;

public interface BadgeCondition<T> {
    boolean isConditionMet(Game game);
    void awardBadge(UserEntity user, String badgeId, String gameId);
    String getBadgeId();
}
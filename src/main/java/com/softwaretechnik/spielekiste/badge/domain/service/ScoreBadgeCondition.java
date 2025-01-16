package com.softwaretechnik.spielekiste.badge.domain.service;

import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;
import com.softwaretechnik.spielekiste.badge.domain.repository.BadgeRepository;
import com.softwaretechnik.spielekiste.badge.infrastructure.persistence.BadgeRepositoryImpl;
import com.softwaretechnik.spielekiste.game.domain.Game;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;

public class ScoreBadgeCondition<T extends Game> implements BadgeCondition<T> {
    private final int requiredScore;
    private final String badgeId;
    private final BadgeRepository badgeRepository = new BadgeRepositoryImpl();

    public ScoreBadgeCondition(String badgeId, int requiredScore) {
        this.requiredScore = requiredScore;
        this.badgeId = badgeId;
    }

    @Override
    public boolean isConditionMet(Game game) {
        return game.getScore() >= requiredScore;
    }

    @Override
    public void awardBadge(UserEntity user, String badgeId, String gameId) {
        BadgeEntity badge = new BadgeEntity();
        badge.setBadgeId(badgeId);
        badge.setGameType(gameId);
        badge.setUserId(user.getId());
        badge.setName("Score Badge " + badgeId);
        badge.setText("This badge is awarded for achieving the required score.");
        badge.setCondition("Score >= " + requiredScore);
        badge.setHasEarned(true);
        badgeRepository.save(badge);
    }

    @Override
    public String getBadgeId() {
        return badgeId;
    }
}
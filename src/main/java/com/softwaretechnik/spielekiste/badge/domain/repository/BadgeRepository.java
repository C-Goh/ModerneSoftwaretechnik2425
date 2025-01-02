package com.softwaretechnik.spielekiste.badge.domain.repository;

import java.util.List;

import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;

public interface BadgeRepository {

    public BadgeEntity findBadgeById(int id);

    public BadgeEntity findBadgeByName(String name);

    public List<BadgeEntity> findAllBadges();
    
    public List<BadgeEntity> findAllBadgesbyGame(int gameId);

    public List<BadgeEntity> findAllEarnedBadges(int userId, boolean hasEarned);

    public List<BadgeEntity> findAllEarnedBadgesbyGame(int userId, int gameId);

    
}

package com.softwaretechnik.spielekiste.badge.application.service;

import java.util.List;

import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;
import com.softwaretechnik.spielekiste.badge.domain.repository.BadgeRepository;

public class BadgeService {

    private final BadgeRepository badgeRepository;

    public BadgeService(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    public BadgeEntity getBadgeById(int id) {
        return badgeRepository.findBadgeById(id);
    }

    public BadgeEntity getBadgeByName(String name) {
        return badgeRepository.findBadgeByName(name);
    }

    public List<BadgeEntity> getAllBadges() {
        return badgeRepository.findAllBadges();
    }

    public List<BadgeEntity> getAllBadgesByGame(int gameId) {
        return badgeRepository.findAllBadgesbyGame(gameId);
    }

    public List<BadgeEntity> getAllEarnedBadges(int userId, boolean hasEarned) {
        return badgeRepository.findAllEarnedBadges(userId, hasEarned);
    }

    public List<BadgeEntity> getAllEarnedBadgesByGame(int userId, int gameId) {
        return badgeRepository.findAllEarnedBadgesbyGame(userId, gameId);
    }

    


}
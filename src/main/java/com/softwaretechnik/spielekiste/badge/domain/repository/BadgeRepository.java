package com.softwaretechnik.spielekiste.badge.domain.repository;

import java.util.List;
import java.util.Optional;

import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;

public interface BadgeRepository {

    List<BadgeEntity> findAll();

    Optional<BadgeEntity> findById(int id);

    List<BadgeEntity> findByUserId(int userId);

    BadgeEntity save(BadgeEntity badge);

    void deleteById(int id);
}
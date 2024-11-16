package com.softwaretechnik.spielekiste.domain.user.service;

import com.softwaretechnik.spielekiste.domain.user.entity.UserEntity;

public class UserDomainService {

    public void validateUser(UserEntity user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }
        if (!isValidName(user.getName())) {
            throw new IllegalArgumentException("User name contains invalid characters");
        }
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z0-9 ]+");
    }
}
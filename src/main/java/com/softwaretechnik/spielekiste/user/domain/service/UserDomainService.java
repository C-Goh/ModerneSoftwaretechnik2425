package com.softwaretechnik.spielekiste.user.domain.service;

import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;

public class UserDomainService {

    public void validateUser(UserEntity user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }
        if (!isValidName(user.getName())) {
            throw new IllegalArgumentException("User name contains invalid characters");
        }
    }

    public boolean isValidName(String name) {
        return name.matches("[a-zA-Z0-9 ]+");
    }
}
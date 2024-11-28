package com.softwaretechnik.spielekiste.user.application.service;

import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;

public class UserContext {

    private static UserEntity currentUser;

    public static void setCurrentUser(UserEntity user) {
        currentUser = user;
    }

    public static UserEntity getCurrentUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }

}
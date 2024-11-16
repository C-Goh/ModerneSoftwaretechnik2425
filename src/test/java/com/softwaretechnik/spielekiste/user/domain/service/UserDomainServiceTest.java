package com.softwaretechnik.spielekiste.domain.user.service;

import com.softwaretechnik.spielekiste.domain.user.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDomainServiceTest {

    private final UserDomainService userDomainService = new UserDomainService();

    @Test
    void validateUser_withValidUser() {
        UserEntity user = new UserEntity();
        user.setName("John Doe");

        assertDoesNotThrow(() -> userDomainService.validateUser(user));
    }

    @Test
    void validateUser_withNullName() {
        UserEntity user = new UserEntity();
        user.setName(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userDomainService.validateUser(user));
        assertEquals("User name cannot be null or empty", exception.getMessage());
    }

    @Test
    void validateUser_withEmptyName() {
        UserEntity user = new UserEntity();
        user.setName("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userDomainService.validateUser(user));
        assertEquals("User name cannot be null or empty", exception.getMessage());
    }

    @Test
    void validateUser_withInvalidName() {
        UserEntity user = new UserEntity();
        user.setName("John@Doe");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userDomainService.validateUser(user));
        assertEquals("User name contains invalid characters", exception.getMessage());
    }
}
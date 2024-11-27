package com.softwaretechnik.spielekiste.user.application.service;

import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserContextTest {

    @BeforeEach
    public void setUp() {
        UserContext.clear();
    }

    @Test
    public void testSetAndGetCurrentUser() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setName("Test User");

        UserContext.setCurrentUser(user);
        UserEntity currentUser = UserContext.getCurrentUser();

        assertNotNull(currentUser);
        assertEquals(1, currentUser.getId());
        assertEquals("Test User", currentUser.getName());
    }

    @Test
    public void testGetCurrentUserWhenNotSet() {
        UserEntity currentUser = UserContext.getCurrentUser();
        assertNull(currentUser);
    }
}

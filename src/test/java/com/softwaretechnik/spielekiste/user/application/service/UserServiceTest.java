package com.softwaretechnik.spielekiste.application.user;

import com.softwaretechnik.spielekiste.domain.user.entity.UserEntity;
import com.softwaretechnik.spielekiste.domain.user.repository.UserRepository;
import com.softwaretechnik.spielekiste.domain.user.service.UserDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDomainService userDomainService;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository, userDomainService);
    }

    @Test
    public void createUser() {
        UserEntity user = new UserEntity();
        user.setName("John Doe");

        userService.createUser(user);

        verify(userDomainService).validateUser(user);
        verify(userRepository).createUser(user);
    }

    @Test
    public void getUserById() {
        UserEntity user = new UserEntity();
        when(userRepository.findUserById(1)).thenReturn(user);

        UserEntity result = userService.getUserById(1);

        assertEquals(user, result);
        verify(userRepository).findUserById(1);
    }

    @Test
    public void updateUser() {
        UserEntity user = new UserEntity();
        user.setName("John Doe");

        userService.updateUser(user);

        verify(userDomainService).validateUser(user);
        verify(userRepository).updateUser(user);
    }

    @Test
    public void deleteUser() {
        int userId = 1;
        userService.deleteUser(userId);

        verify(userRepository).deleteUser(userId);
    }

    @Test
    public void findAllUsers() {
        List<UserEntity> users = List.of(new UserEntity(), new UserEntity());
        when(userRepository.findAllUsers()).thenReturn(users);

        List<UserEntity> result = userService.findAllUsers();

        assertEquals(users, result);
        verify(userRepository).findAllUsers();
    }
}

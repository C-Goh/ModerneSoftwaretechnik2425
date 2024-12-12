package com.softwaretechnik.spielekiste.user.application.service;

import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;
import com.softwaretechnik.spielekiste.user.domain.service.UserDomainService;

import java.util.List;

/**
 * Service class for managing users.
 */
public class UserService {

    private final UserRepository userRepository;

    private final UserDomainService userDomainService;

    /**
     * Constructs a new UserService with the given repositories.
     *
     * @param userRepository the user repository
     * @param userDomainService the user domain service
     */
    public UserService(UserRepository userRepository, UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userDomainService = userDomainService;
    }

    /**
     * Creates a new user.
     *
     * @param user the user entity
     */
    public void createUser(UserEntity user) {
        userDomainService.validateUser(user);
        // TODO return when invalid

        userRepository.createUser(user);
    }

    public UserEntity getUserById(int id) {
        return userRepository.findUserById(id);
    }

    public void updateUser(UserEntity user) {
        userDomainService.validateUser(user);
        userRepository.updateUser(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public UserEntity getCurrentUser() {
        return UserContext.getCurrentUser();
    }
}
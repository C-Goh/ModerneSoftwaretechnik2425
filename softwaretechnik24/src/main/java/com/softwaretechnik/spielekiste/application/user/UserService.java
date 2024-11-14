package com.softwaretechnik.spielekiste.application.user;

import com.softwaretechnik.spielekiste.domain.user.entity.UserEntity;
import com.softwaretechnik.spielekiste.domain.user.repository.UserRepository;
import com.softwaretechnik.spielekiste.domain.user.service.UserDomainService;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    private final UserDomainService userDomainService;

    public UserService(UserRepository userRepository, UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userDomainService = userDomainService;
    }

    public void createUser(UserEntity user) {
        userDomainService.validateUser(user);
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
}
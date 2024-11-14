package com.softwaretechnik.spielekiste.domain.user.repository;

import com.softwaretechnik.spielekiste.domain.user.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    void createUser(UserEntity user);

    UserEntity findUserById(int id);

    List<UserEntity> findAllUsers();

    void updateUser(UserEntity user);

    void deleteUser(int id);
}

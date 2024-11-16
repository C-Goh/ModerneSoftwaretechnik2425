package com.softwaretechnik.spielekiste.domain.user.repository;

import com.softwaretechnik.spielekiste.domain.user.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    public void createUser(UserEntity user);

    public UserEntity findUserById(int id);

    public List<UserEntity> findAllUsers();

    public void updateUser(UserEntity user);

    public void deleteUser(int id);
}

package com.softwaretechnik.spielekiste.user.domain.repository;

import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {
    public void createUser(UserEntity user);

    public UserEntity findUserById(int id);

    public UserEntity findUserByName(String name);

    public List<UserEntity> findAllUsers();

    public void updateUser(UserEntity user);

    public void deleteUser(int id);

    public void saveGamePoints(int userId, int gameId, int points);
}

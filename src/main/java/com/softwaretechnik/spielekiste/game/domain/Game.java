package com.softwaretechnik.spielekiste.game.domain;

import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;

public interface Game {
    void start();
    void end();
    void setScore(int score);
    int getScore();
    void setUser(UserEntity user);
    UserEntity getUser();
    void setGameBadgesCondition();
    void setGameType(String gameType);
    String getGameType();
}
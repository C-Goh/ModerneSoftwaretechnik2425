package com.softwaretechnik.spielekiste.game.service;


import com.softwaretechnik.spielekiste.game.domain.Game;

public class GameService {
    public void startGame(Game game) {
        game.start();
    }

    public void endGame(Game game) {
        game.end();
    }
}

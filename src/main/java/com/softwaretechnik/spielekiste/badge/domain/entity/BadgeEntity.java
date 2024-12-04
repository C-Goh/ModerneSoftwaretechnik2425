package com.softwaretechnik.spielekiste.badge.domain.entity;

public class BadgeEntity {

    private int id;

    private int gameId;

    private String name;

    private String text;

    private boolean hasEarned;

    private int userId;

    public BadgeEntity() {
    }

    public BadgeEntity(int id, int gameId, String name, 
    String text, boolean hasEarned, int userId) {

        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.text = text;
        this.hasEarned = hasEarned;
        this.userId = userId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getHasEarned() {
        return hasEarned;
    }

    public void setHasEarned(boolean hasEarned) {
        this.hasEarned = hasEarned;
    }
    
}

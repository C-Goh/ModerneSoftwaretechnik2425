package com.softwaretechnik.spielekiste.badge.domain.entity;

public class BadgeEntity {

    private int id;
    private String badgeId;
    private String gameType;
    private String name;
    private String text;
    private String condition;
    private boolean hasEarned;
    private int userId;

    public BadgeEntity() {
    }

    public BadgeEntity(int id, String badgeId, String gameType, String name, String text, String condition, boolean hasEarned, int userId) {
        this.id = id;
        this.badgeId = badgeId;
        this.gameType = gameType;
        this.name = name;
        this.text = text;
        this.condition = condition;
        this.hasEarned = hasEarned;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean getHasEarned() {
        return hasEarned;
    }

    public void setHasEarned(boolean hasEarned) {
        this.hasEarned = hasEarned;
    }
}
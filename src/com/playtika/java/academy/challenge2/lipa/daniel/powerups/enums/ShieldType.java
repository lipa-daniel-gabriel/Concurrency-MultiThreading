package com.playtika.java.academy.challenge2.lipa.daniel.powerups.enums;

public enum ShieldType {
    Strong("TELEPORT"), Weak("TAKING HITS"), Invincible("HEAL");

    private String specialAction;

    ShieldType(String specialAction) {
        this.specialAction = specialAction;
    }

    public String getSpecialAction() {
        return specialAction;
    }
}

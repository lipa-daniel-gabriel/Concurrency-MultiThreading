package com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes;

import com.playtika.java.academy.challenge2.lipa.daniel.powerups.enums.ShieldType;

public class BrokenShield extends BonusShield {

    private int additionalDamage;

    public BrokenShield(int score, String name, ShieldType shieldType, int additionalDamage) {
        super(score, name, shieldType);
        this.setAdditionalDamage(additionalDamage);
    }

    public void setAdditionalDamage(int additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    public void disable() {
        setAdditionalDamage(0);
    }

    @Override
    public void takesAHit(int dmg) {
        setScore(getScore() - dmg - additionalDamage);
    }

    @Override
    public void hits(int dmg) {
        setScore(getScore() + dmg - additionalDamage);
    }
}


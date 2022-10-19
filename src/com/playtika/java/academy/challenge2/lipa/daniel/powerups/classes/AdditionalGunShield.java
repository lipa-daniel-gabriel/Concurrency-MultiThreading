package com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes;

import com.playtika.java.academy.challenge2.lipa.daniel.powerups.enums.ShieldType;

public class AdditionalGunShield extends BonusShield {

    private String ability;
    private int scoreMultiplier;

    public AdditionalGunShield(int score, String name, ShieldType shieldType, String ability, int scoreMultiplier) {
        super(score, name, shieldType);
        this.ability = ability;
        this.setScoreMultiplier(scoreMultiplier);

    }

    public String getAbility() {
        return ability;
    }

    public void useAdditionalGun() {
        super.isAdvantage = true;
        super.hits(scoreMultiplier);
    }

    public void setScoreMultiplier(int scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
    }

    @Override
    public void hits(int dmg) {
        setScore(getScore() + dmg * scoreMultiplier);
    }

    @Override
    public void takesAHit(int dmg) {
        setScore(getScore() - dmg * scoreMultiplier);
    }
}

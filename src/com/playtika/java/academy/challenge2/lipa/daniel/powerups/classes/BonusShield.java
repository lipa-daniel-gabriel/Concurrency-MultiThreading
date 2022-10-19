package com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes;

import com.playtika.java.academy.challenge2.lipa.daniel.powerups.enums.ShieldType;

import java.util.Comparator;
import java.util.function.IntSupplier;


public class BonusShield extends Shield {
    protected boolean isAdvantage;
    private int score;
    private String name;
    private ShieldType shieldType;

    public BonusShield(int score, String name, ShieldType shieldType) {
        this.score = score;
        this.name = name;
        this.shieldType = shieldType;
    }

    public BonusShield(boolean isAdvantage, int score, String name, ShieldType shieldType) {
        this.isAdvantage = isAdvantage;
        this.score = score;
        this.name = name;
        this.shieldType = shieldType;
    }

    public boolean isAdvantage() {
        if (shieldType.getSpecialAction().equals("HEAL") || shieldType.getSpecialAction().equals("TELEPORT")) {
            return true;
        } else return false;
    }

    public void userBonusPoints(IntSupplier supplier) {
        this.score += supplier.getAsInt();
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void takesAHit(int dmg) {
        this.score = score - dmg;
    }

    @Override
    public void hits(int dmg) {
        this.score += dmg;
    }


    public static int compareByAgeThenName(BonusShield bonusShield1, BonusShield bonusShield2) {
        if (bonusShield1.getScore() == bonusShield2.getScore()) {

            return bonusShield1.name.compareTo(bonusShield2.name);
        } else {
            return Integer.compare(bonusShield1.getScore(), bonusShield2.getScore());
        }
    }

    public static int compareByScore(BonusShield b1, BonusShield b2) {
        return b1.getScore()-b2.getScore();
    }

    public ShieldType getShieldType() {
        return shieldType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BonusShield{");
        sb.append("isAdvantage=").append(isAdvantage);
        sb.append(", score=").append(score);
        sb.append(", name='").append(name).append('\'');
        sb.append(", shieldType=").append(shieldType);
        sb.append('}');
        return sb.toString();
    }

}

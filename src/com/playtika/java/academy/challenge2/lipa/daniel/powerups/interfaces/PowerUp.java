package com.playtika.java.academy.challenge2.lipa.daniel.powerups.interfaces;

public interface PowerUp {

    final int MAX_POINTS = 100;

    public void takesAHit(int dmg);

    public void hits(int dmg);
}

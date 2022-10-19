package com.playtika.java.academy.challenge2.lipa.daniel.statistics.interfaces;

import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;

import java.util.Collection;
import java.util.List;

public interface Processable {

    public BonusShield[] process(List<BonusShield> bonusShieldCollection);
}

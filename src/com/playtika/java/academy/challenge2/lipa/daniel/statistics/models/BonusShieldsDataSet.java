package com.playtika.java.academy.challenge2.lipa.daniel.statistics.models;

import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.enums.ShieldType;
import com.playtika.java.academy.challenge2.lipa.daniel.statistics.interfaces.Processable;

import java.util.*;
import java.util.stream.Collectors;

public class BonusShieldsDataSet implements Cloneable, Processable {

    private List<BonusShield> shields;

    public BonusShieldsDataSet(List<BonusShield> powerUpsSettings) {
        this.shields=powerUpsSettings;
    }


    public void printToFile(String fileName) {
        System.out.println("Printing file");
    }

    public Collection<BonusShield> getShields() {
        return shields;
    }

    public void addShield(BonusShield shield) {
        shields.add(shield);
    }

    public BonusShield getBonusShield(String name) {
        BonusShield reqBonusShield = null;
        for (BonusShield bonusShield : shields) {
            if (bonusShield.getName().equals(name)) {
                reqBonusShield = bonusShield;
            }
        }
        return reqBonusShield;
    }

    public void removeBonusShield(String name) {
        this.shields = this.shields.stream()
                .filter(x -> x.getName().equals(name))
                .collect(Collectors.toList());
    }

    public BonusShield[] process(Processable processor){
        return processor.process(shields);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public BonusShield[] process(List<BonusShield> bonusShieldCollection) {
        return new BonusShield[0];
    }
}

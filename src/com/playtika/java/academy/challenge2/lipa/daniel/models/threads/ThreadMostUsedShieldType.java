package com.playtika.java.academy.challenge2.lipa.daniel.models.threads;

import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.enums.ShieldType;
import com.playtika.java.academy.challenge2.lipa.daniel.statistics.interfaces.Processable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ThreadMostUsedShieldType implements Processable {
    @Override
    public BonusShield[] process(List<BonusShield> bonusShields) {

        CompletableFuture<BonusShield[]> taskAsync = CompletableFuture.supplyAsync(() -> {

            ShieldType type = bonusShields.parallelStream()
                    .collect(Collectors.groupingBy(BonusShield::getShieldType, Collectors.counting()))
                    .entrySet()
                    .parallelStream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();
            return bonusShields.parallelStream().filter(x -> x.getShieldType().equals(type)).toArray(BonusShield[]::new);
        });

        BonusShield[] result;
        try {
            result = taskAsync.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

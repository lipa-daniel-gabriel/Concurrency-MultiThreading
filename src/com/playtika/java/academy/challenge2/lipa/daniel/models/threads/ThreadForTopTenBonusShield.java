package com.playtika.java.academy.challenge2.lipa.daniel.models.threads;

import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;
import com.playtika.java.academy.challenge2.lipa.daniel.statistics.interfaces.Processable;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThreadForTopTenBonusShield implements Processable {
    @Override
    public BonusShield[] process(List<BonusShield> bonusShieldCollection) {

        CompletableFuture<BonusShield[]> taskAsync = CompletableFuture.supplyAsync(() -> bonusShieldCollection.parallelStream()
                .sorted((Comparator.comparing(BonusShield::getScore).reversed()))
                .limit(10).toArray(BonusShield[]::new));

        BonusShield[] result;
        try {
            result = taskAsync.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}

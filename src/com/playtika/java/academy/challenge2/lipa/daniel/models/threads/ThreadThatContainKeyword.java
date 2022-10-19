package com.playtika.java.academy.challenge2.lipa.daniel.models.threads;

import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;
import com.playtika.java.academy.challenge2.lipa.daniel.statistics.interfaces.Processable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ThreadThatContainKeyword implements Processable {
    private final String KEYWORD = "atomic";

    @Override
    public BonusShield[] process(List<BonusShield> bonusShields) {
        CompletableFuture<BonusShield[]> taskAsync = CompletableFuture.supplyAsync(() -> {

            double average = bonusShields.parallelStream()
                    .mapToInt(BonusShield::getScore)
                    .average()
                    .getAsDouble();

            return bonusShields.parallelStream()
                    .filter(x -> x.getName().toLowerCase().contains(KEYWORD.toLowerCase()))
                    .filter(x -> x.getScore() > average).toArray(BonusShield[]::new);
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

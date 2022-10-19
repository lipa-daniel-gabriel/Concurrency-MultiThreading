package com.playtika.java.academy.challenge2.lipa.daniel.models.threads;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class OnlinePlayersThread {

    public static void returnOnlinePLayers() throws InterruptedException {
        CompletableFuture<Void> taskAsync = CompletableFuture.supplyAsync(() -> {
            try {
                Random random1 = new Random();
                TimeUnit.SECONDS.sleep(5);
                return random1.nextInt(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenAcceptAsync((x) -> {
            System.out.println("Online players: " + x);
        });
        TimeUnit.SECONDS.sleep(6);
    }
}

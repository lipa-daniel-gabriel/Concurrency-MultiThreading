package com.playtika.java.academy.challenge2.lipa.daniel.spaceInvaders;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class RandomInvadersSupplier extends Thread {
    volatile boolean hasFinishedGeneratingData = false;
    int initialSeed;

    LinkedBlockingDeque<SpaceInvader> spaceInvaders;

    public RandomInvadersSupplier(LinkedBlockingDeque<SpaceInvader> spaceInvaders, int initialSeed) {
        this.initialSeed = initialSeed;
        this.spaceInvaders = spaceInvaders;
    }

    public void stopGenerating() {
        this.hasFinishedGeneratingData = true;
    }

    @Override
    public void run() {
        Random random = new Random(initialSeed);
        while (!hasFinishedGeneratingData) {
            try {
                this.spaceInvaders.put(new SpaceInvader(random.nextInt(10), random.nextInt(20), random.nextBoolean()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
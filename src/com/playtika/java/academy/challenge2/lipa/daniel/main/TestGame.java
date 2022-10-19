package com.playtika.java.academy.challenge2.lipa.daniel.main;

import com.playtika.java.academy.challenge2.lipa.daniel.exceptions.PlayerProfileException;
import com.playtika.java.academy.challenge2.lipa.daniel.interfaces.ScoreDifference;
import com.playtika.java.academy.challenge2.lipa.daniel.models.*;
import com.playtika.java.academy.challenge2.lipa.daniel.models.threads.*;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.AdditionalGunShield;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BrokenShield;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.enums.ShieldType;
import com.playtika.java.academy.challenge2.lipa.daniel.spaceInvaders.GameSession;
import com.playtika.java.academy.challenge2.lipa.daniel.spaceInvaders.RandomInvadersSupplier;
import com.playtika.java.academy.challenge2.lipa.daniel.spaceInvaders.SpaceInvader;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

public class TestGame {
    public static void main(String[] args) throws PlayerProfileException, CloneNotSupportedException, IOException, ExecutionException, InterruptedException {

        System.out.println("Java Fundamentals / Java Advanced");

        PlayerProfile playerProfile1 = new PlayerProfile("Daniel", "dan.lipa99@yahoo.com");
        PlayerProfile playerProfile2 = new PlayerProfile("Mihai", "mihai99@yahoo.com");
        System.out.println(playerProfile1);
        System.out.println(playerProfile2);

        playerProfile1.setMinutesPlayedPerSession(new int[]{1, 2, 3, 4});
        playerProfile1.addNewPlaySession(22);
        playerProfile1.addNewPlaySession(33);
        playerProfile1.addNewPlaySession(44);
        playerProfile1.addNewPlaySession(55);
        System.out.println(playerProfile1);

        PlayerProfile clone = (PlayerProfile) playerProfile1.clone();
        clone.setEmail("testClone@email");
        System.out.println(playerProfile1);
        System.out.println(clone);

        System.out.println(playerProfile1.getEmail());
        System.out.println(playerProfile1.getMaxPlayedMinutes());
        System.out.println(playerProfile1.getPlayerAgeInDays());
        System.out.println(playerProfile1.getUserName());
        System.out.println(playerProfile1.getTotalPlayedTime());
        System.out.println(PlayerProfile.getNbOfProfiles());
        System.out.println(playerProfile1);

        AdditionalGunShield additionalGunShield = new AdditionalGunShield(1000, "Pistol", ShieldType.Strong, "Kill", 2);
        additionalGunShield.setScoreMultiplier(5);
        additionalGunShield.hits(1);
        System.out.println(additionalGunShield.getScore());
        additionalGunShield.takesAHit(3);
        System.out.println(additionalGunShield.getScore());


        BrokenShield brokenShield = new BrokenShield(500, "Useless", ShieldType.Weak, 10);
        brokenShield.disable();
        brokenShield.takesAHit(2);
        brokenShield.hits(4);
        System.out.println(brokenShield.getScore());

        Random random = new Random();
        int rand = random.nextInt(1000);

        BonusShield bonusShield = new BonusShield(500, "brokenShield", ShieldType.Strong);
        playerProfile1.addPowerUp(bonusShield);
        IntSupplier supplier = () -> rand;
        bonusShield.userBonusPoints(supplier);

        ScoreDifference scoreDifference = ((a, b) -> a - b);
        int scoreDiff = scoreDifference.scoreDifferenceBetweenAddShieldGunAndBrokenShield(bonusShield.getScore(), additionalGunShield.getScore());
        System.out.println(scoreDiff);

        playerProfile1.addPowerUp(additionalGunShield, brokenShield);
        System.out.println(playerProfile1.getPowerUp("brokenShield"));
        playerProfile1.removePowerUp(additionalGunShield.getName());
        System.out.println(playerProfile1.getPowerUp(BrokenShield.class));
        TimeUnit.SECONDS.sleep(3);


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Java Concurrency and MultiThreading");

        Timer timer = new Timer();
        timer.schedule(new Loading(), 0, 1500);
        PlayerProfile playerProfile = ImportedData.importPlayerProfile(args);
        List<BonusShield> powerUpsSettings = ImportedData.importBonusShields(args);
        GameSettings gameSettings = ImportedData.importGameSettings(args);
        timer.cancel();

        System.out.println("All data is loaded");

        powerUpsSettings.sort(Comparator.comparing(BonusShield::getScore).reversed().thenComparing(BonusShield::getName));
        List<BonusShield> requiredAmount = powerUpsSettings.stream().limit(50).collect(Collectors.toList());
        requiredAmount.forEach(System.out::println);

        ThreadBackup.saveBackup(gameSettings);
        OnlinePlayersThread.returnOnlinePLayers();

        System.out.println("**********Space invaders *******************");


        LinkedBlockingDeque<SpaceInvader> spaceInvaders = new LinkedBlockingDeque<>(200);

        RandomInvadersSupplier invadersSupplier = new RandomInvadersSupplier(spaceInvaders, 100);
        invadersSupplier.start();

        GameSession game = new GameSession(4, spaceInvaders);
        TimeUnit.SECONDS.sleep(2);
        invadersSupplier.stopGenerating();

        game.start();
        TimeUnit.SECONDS.sleep(30);
        game.setGameIsFinished(true);
        invadersSupplier.join();
        game.join();
        System.out.println("Game is finished");

        System.out.println("STAGE 4");
        StreamsManager streamsManager = new StreamsManager();
        streamsManager.startEachStreamOnANewThread(powerUpsSettings);
        System.out.println("Its over");
    }
}

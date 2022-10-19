package com.playtika.java.academy.challenge2.lipa.daniel.models;

import com.playtika.java.academy.challenge2.lipa.daniel.exceptions.NoGameSettingsException;
import com.playtika.java.academy.challenge2.lipa.daniel.exceptions.NoPlayerProfileException;
import com.playtika.java.academy.challenge2.lipa.daniel.exceptions.PlayerProfileException;
import com.playtika.java.academy.challenge2.lipa.daniel.models.threads.ThreadPowerUpsFile;
import com.playtika.java.academy.challenge2.lipa.daniel.models.threads.ThreadProfileFile;
import com.playtika.java.academy.challenge2.lipa.daniel.models.threads.ThreadSettingsFile;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImportedData {

    public static GameSettings importGameSettings(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        File gameSettingsFile = new File(args[0]);
        if (!gameSettingsFile.exists()) {
            throw new NoGameSettingsException("No game settings available");
        }
        Future<GameSettings> gameSettingsFuture = executorService.submit(new ThreadSettingsFile(gameSettingsFile));
        GameSettings gameSettings = gameSettingsFuture.get();
        return gameSettings;
    }

    public static PlayerProfile importPlayerProfile(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        File playerProfileFile = new File(args[1]);
        if (!playerProfileFile.exists()) {
            throw new PlayerProfileException("Player profile not available");
        }
        Future<PlayerProfile> playerProfileFuture = executorService.submit(new ThreadProfileFile(playerProfileFile));
        PlayerProfile playerProfile = playerProfileFuture.get();
        return playerProfile;
    }

    public static List<BonusShield> importBonusShields(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        File powerUpFile = new File(args[2]);
        if (!powerUpFile.exists()) {
            throw new NoPlayerProfileException("Player data not existing");
        }
        Future<List<BonusShield>> bonusShieldFuture = executorService.submit(new ThreadPowerUpsFile(powerUpFile));
        List<BonusShield> bonusShieldList = bonusShieldFuture.get();
        return bonusShieldList;
    }

}

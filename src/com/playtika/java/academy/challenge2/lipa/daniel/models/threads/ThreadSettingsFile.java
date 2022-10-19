package com.playtika.java.academy.challenge2.lipa.daniel.models.threads;

import com.playtika.java.academy.challenge2.lipa.daniel.exceptions.NoGameSettingsException;
import com.playtika.java.academy.challenge2.lipa.daniel.models.GameSettings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Callable;

public class ThreadSettingsFile implements Callable<GameSettings> {

    private File settings;

    public ThreadSettingsFile(File settings) {
        this.settings = settings;
    }

    @Override
    public GameSettings call() throws Exception {

        if(!settings.exists()){
            throw new NoGameSettingsException("Game settings not existing");
        }

        FileReader fileReader = new FileReader(settings);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String clientVersion = bufferedReader.readLine().split("=")[1];
        String autoSave = bufferedReader.readLine().split("=")[1];
        int autoSaveTimeout = Integer.parseInt(bufferedReader.readLine().split("=")[1]);
        String serverUrl = bufferedReader.readLine().split("=")[1];

        GameSettings gameSettings = new GameSettings(clientVersion, autoSaveTimeout, serverUrl);
        if (autoSave.equals(true)) {
            gameSettings.startAutoSave();
        }

        bufferedReader.close();
        return gameSettings;
    }
}

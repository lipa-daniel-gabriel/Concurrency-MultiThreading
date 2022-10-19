package com.playtika.java.academy.challenge2.lipa.daniel.models.threads;

import com.playtika.java.academy.challenge2.lipa.daniel.exceptions.NoPlayerProfileException;
import com.playtika.java.academy.challenge2.lipa.daniel.models.PlayerProfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

public class ThreadProfileFile implements Callable<PlayerProfile> {

    private File settings;

    public ThreadProfileFile(File settings) {
        this.settings = settings;
    }

    @Override
    public PlayerProfile call() throws Exception {

        if(!settings.exists()){
            throw new NoPlayerProfileException("No player profiles");
        }

        FileReader fileReader = new FileReader(settings);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String userName = bufferedReader.readLine();
        String email = bufferedReader.readLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime localDateTime = LocalDate.parse(bufferedReader.readLine(), formatter)
                .atStartOfDay();
        int[] minutesPlayedPerSession = bufferedReader.readLine().chars().toArray();
        PlayerProfile playerSettings = new PlayerProfile(userName, email, localDateTime,minutesPlayedPerSession);



        bufferedReader.close();
        return playerSettings;
    }
}

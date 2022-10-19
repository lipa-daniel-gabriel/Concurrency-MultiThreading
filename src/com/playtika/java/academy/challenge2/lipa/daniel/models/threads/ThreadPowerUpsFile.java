package com.playtika.java.academy.challenge2.lipa.daniel.models.threads;

import com.playtika.java.academy.challenge2.lipa.daniel.exceptions.NoPlayerDataException;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.enums.ShieldType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ThreadPowerUpsFile implements Callable<List<BonusShield>> {

    private File settings;

    public ThreadPowerUpsFile(File settings) {
        this.settings = settings;
    }

    @Override
    public List<BonusShield> call() throws Exception {

        if(!settings.exists()){
            throw new NoPlayerDataException("Player data not existing");
        }

        FileReader fileReader = new FileReader(settings);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<BonusShield> bonusShieldList = new ArrayList<>();

        String buffer = "";

        while ((buffer = bufferedReader.readLine()) != null) {

            boolean isAdvantage = Boolean.parseBoolean(buffer.split(",")[0]);
            int score = Integer.parseInt(buffer.split(",")[1]);
            String name = (buffer.split(",")[2]);
            ShieldType shieldType = ShieldType.valueOf(buffer.split(",")[3]);

            BonusShield bonusShield1 = new BonusShield(isAdvantage, score, name, shieldType);
            bonusShieldList.add(bonusShield1);
        }

        bufferedReader.close();

        return bonusShieldList;
    }
}

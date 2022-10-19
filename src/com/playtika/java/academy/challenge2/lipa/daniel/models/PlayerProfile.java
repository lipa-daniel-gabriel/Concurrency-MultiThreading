package com.playtika.java.academy.challenge2.lipa.daniel.models;

import com.playtika.java.academy.challenge2.lipa.daniel.exceptions.PlayerProfileException;
import com.playtika.java.academy.challenge2.lipa.daniel.interfaces.Restorable;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.Shield;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.interfaces.PowerUp;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerProfile implements Cloneable, Restorable {

    private String userName;
    private String email;
    private LocalDateTime creationDate;
    private int[] minutesPlayedPerSession;
    private static int NB_OF_PROFILES;
    private List<PowerUp> powerUps = new ArrayList<>();

    public PlayerProfile(String userName, String email) throws PlayerProfileException {
        this.userName = userName;
        this.setEmail(email);
        this.creationDate = LocalDateTime.now();
        this.NB_OF_PROFILES++;
    }

    public PlayerProfile(String userName, String email, LocalDateTime creationDate, int[] minutesPlayedPerSession) {
        this.userName = userName;
        this.email = email;
        this.creationDate = creationDate;
        this.minutesPlayedPerSession = minutesPlayedPerSession;
    }

    public int[] getMinutesPlayedPerSession() {
        return minutesPlayedPerSession;
    }

    public void addPowerUp(PowerUp... powerUpShields) {
        if(powerUps == null){
            powerUps = new ArrayList<>();
        }
        powerUps.addAll(List.of(powerUpShields));
    }


    public void removePowerUp(String name) {
        PowerUp powerUpToRemove = getPowerUp(name);
        if(powerUpToRemove != null) {
            powerUps.remove(powerUpToRemove);
        }
    }

    public PowerUp getPowerUp(String name) {
        for (PowerUp powerUp : powerUps) {
            if(powerUp instanceof BonusShield) {
                BonusShield bonusShield = (BonusShield) powerUp;
                if (bonusShield.getName().equals(name)) {
                    return powerUp;
                }
            }
        }
        throw new UnsupportedOperationException("This PowerUp can not be found.");
    }


    public PowerUp getPowerUp(Class<?> type) {
        for (PowerUp powerUp : powerUps) {
            if (powerUp.getClass() == type) {
                return powerUp;
            }
        }
        throw new UnsupportedOperationException("This PowerUp could not be found.");
    }

    public int[] getMinutesPlayedPerSessionClone() throws PlayerProfileException {
        if (minutesPlayedPerSession == null) {
            throw new PlayerProfileException("Null");
        }
        int[] clone = new int[minutesPlayedPerSession.length];
        System.arraycopy(minutesPlayedPerSession, 0, clone, 0, minutesPlayedPerSession.length);
        return clone;
    }

    public void setEmail(String email) throws PlayerProfileException {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            this.email = email;
        } else throw new PlayerProfileException("Its not a valid email");
    }

    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getTotalPlayedTime() {
        int sum = 0;
        for (int eachSession : minutesPlayedPerSession) {
            sum += eachSession;
        }
        return sum;
    }


    public int getPlayerAgeInDays() {
        LocalDateTime localDate = LocalDateTime.now();
        int playerAge = (int) ChronoUnit.DAYS.between(creationDate, localDate);
        return playerAge;
    }

    public int getMaxPlayedMinutes() {
        int[] copyOfArray = new int[this.minutesPlayedPerSession.length];
        System.arraycopy(minutesPlayedPerSession, 0, copyOfArray, 0, minutesPlayedPerSession.length);
        Arrays.sort(copyOfArray);
        return copyOfArray[copyOfArray.length - 1];
    }

    public void addNewPlaySession(int time) {
        int[] copyOfArr = new int[minutesPlayedPerSession.length + 1];
        System.arraycopy(minutesPlayedPerSession, 0, copyOfArr, 0, minutesPlayedPerSession.length);
        minutesPlayedPerSession = Arrays.copyOf(copyOfArr, copyOfArr.length);
        updateLastPlayedTime(time);
    }

    public void updateLastPlayedTime(int updateTime) {
        this.minutesPlayedPerSession[this.minutesPlayedPerSession.length - 1] = updateTime;
    }

    public void setMinutesPlayedPerSession(int[] minutesPlayedPerSession) throws PlayerProfileException {
        if (minutesPlayedPerSession == null) {
            minutesPlayedPerSession = new int[0];
            throw new PlayerProfileException("minutes per session not valid");
        }
        this.minutesPlayedPerSession = minutesPlayedPerSession;
    }

    public static int getNbOfProfiles() {
        return NB_OF_PROFILES;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PlayerProfile clonedPlayerProfile = (PlayerProfile) super.clone();
        clonedPlayerProfile.creationDate = creationDate;
        clonedPlayerProfile.minutesPlayedPerSession = Arrays.copyOf(minutesPlayedPerSession, minutesPlayedPerSession.length);
        return clonedPlayerProfile;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PlayerProfile{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", minutesPlayedPerSession=");
        if (minutesPlayedPerSession == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < minutesPlayedPerSession.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(minutesPlayedPerSession[i]);
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void saveProfile(String fileName) throws IOException {
        File logFile = new File("logs.txt");
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(logFile, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(this.toString());
        printWriter.close();
    }

    @Override
    public void loadProfile(String fileName) throws IOException {

        File logFile = new File("logs.txt");
        FileReader fileReader = new FileReader(logFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = "";

        while ((textLine = bufferedReader.readLine()) != null) {
            System.out.println(textLine);
        }

        bufferedReader.close();
    }
}

package com.playtika.java.academy.challenge2.lipa.daniel.models.threads;

import com.playtika.java.academy.challenge2.lipa.daniel.models.GameSettings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class ThreadBackup implements Runnable {

    private File settings;
    private int pause;
    boolean stoped = false;


    public ThreadBackup(File backup, int autoSaveTimeoutInSeconds) {
        this.settings = backup;
        this.pause = autoSaveTimeoutInSeconds;
    }

    public void setStoped() {
        this.stoped = true;
    }

    @Override
    public void run() {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(settings,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            while (!stoped) {
                printWriter.println("Autosave: " + new Timestamp(System.currentTimeMillis()));
                TimeUnit.SECONDS.sleep(pause);
            }
            printWriter.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveBackup(GameSettings gameSettings) throws InterruptedException {
        System.out.println("Starting auto-save");
        File backup = new File("backup.txt");
        if (!backup.exists()) {
            try {
                backup.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ThreadBackup threadBackup = new ThreadBackup(backup, gameSettings.getAutoSaveTimeoutInSeconds());
        Thread thread = new Thread(threadBackup);
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        threadBackup.setStoped();
        System.out.println("Auto-save finished");

    }
}

package com.playtika.java.academy.challenge2.lipa.daniel.spaceInvaders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class GameSession extends Thread{
    private int difficultyLevel;
    private int score;
    private LinkedBlockingDeque<SpaceInvader> spaceInvaders;

    private List<SpaceInvader> spaceInvaderList = new ArrayList<>(20);

    public boolean gameIsFinished;

    public GameSession(int difficultyLevel , LinkedBlockingDeque<SpaceInvader> linkedBlockingDeque) {
        this.difficultyLevel = difficultyLevel;
        this.spaceInvaders = linkedBlockingDeque;
    }

    public void addSpaceInvader(SpaceInvader spaceInvader){
        spaceInvaders.add(spaceInvader);
    }

    public int getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public int getScore() {
        return this.score;
    }

    public LinkedBlockingDeque<SpaceInvader> getSpaceInvaders() {
        return this.spaceInvaders;
    }

    public void increaseDifficulty(){
        this.difficultyLevel++;
    }

    public void reduceDifficulty(){
        if(this.difficultyLevel >=1) {
            this.difficultyLevel--;
        }
    }

    public void setGameIsFinished(boolean gameIsFinished) {
        this.gameIsFinished = gameIsFinished;
    }

    public void print(){
        for(SpaceInvader spaceInvader : spaceInvaderList){
            System.out.println(spaceInvader);
        }
    }

    @Override
    public void run() {
        System.out.println("Game has started with 20 space invaders");
        for(int i = 0 ; i < 20 ; i++){
            try {
                spaceInvaderList.add(spaceInvaders.takeFirst());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        while(!gameIsFinished) {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Remove invader: " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SpaceInvader spaceInvader = spaceInvaderList.remove(spaceInvaderList.size() - 1);
                System.out.println("Coordinates: X:" +spaceInvader.getX()+ " Y:"+spaceInvader.getY());
            }

            for (int i = 1; i <= 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    SpaceInvader spaceInvader = spaceInvaders.takeFirst();
                    System.out.println("Add invader: " + i);
                    System.out.println("Coordinates: X:" +spaceInvader.getX()+ " Y:"+spaceInvader.getY());
                    spaceInvaderList.add(spaceInvader);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("**********************************************");
        }
        System.out.println("The game is over");

    }
}

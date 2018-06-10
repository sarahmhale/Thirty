package com.example.sarahhale.thirty.playlogic;

public class Counter {
    private int totalThrows;
    private int totalRounds;
    private int currentRounds;
    private int currentThrows;
    private boolean gameFinished = false;
    private boolean newRound = false;

    public Counter (int totalRounds, int totalThrows){
        this.totalRounds= totalRounds;
        this.totalThrows=totalThrows;
    }
    public int getRounds() {
        return currentRounds;
    }

    public int getThrows() {
        return currentThrows;
    }

    public void addRound() {
        currentRounds += 1;
        if(currentRounds == totalRounds){
            gameFinished = true;
            currentThrows=0;
            currentRounds=0;
        }
    }

    public void addThrow() {
        currentThrows += 1;
        if(currentThrows == totalThrows){
            newRound =true;
        }
        else if( currentThrows > totalThrows){
            newRound= false;
            currentThrows = 0;
        }

    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public boolean isItANewRound() {
        return newRound;
    }
}

package com.example.sarahhale.thirty.playlogic;

public class Counter {
    private int totalThrows;
    private int totalRounds;
    private int currentRounds;
    private int currentThrows;
    private boolean gameFinished = false;

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
        if(currentRounds >= totalRounds){
            System.out.println("in hehre");
            gameFinished = true;
            currentThrows=0;
            currentRounds=0;
        }
    }

    public void addThrow() {
        currentThrows += 1;

        if( currentThrows >= totalThrows){
            addRound();
            currentThrows = 0;
        }
    }

    public boolean isGameFinished() {
        return gameFinished;
    }
}

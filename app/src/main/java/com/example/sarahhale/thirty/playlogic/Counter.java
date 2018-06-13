package com.example.sarahhale.thirty.playlogic;

import android.os.Parcel;
import android.os.Parcelable;

public class Counter implements Parcelable{
    private final int totalThrows;
    private final int totalRounds;
    private int currentRounds;
    private int currentThrows;
    private boolean gameFinished = false;
    private boolean newRound = false;

    public Counter (int totalRounds, int totalThrows){
        this.totalRounds= totalRounds;
        this.totalThrows=totalThrows;
    }

    protected Counter(Parcel in) {
        totalThrows = in.readInt();
        totalRounds = in.readInt();
        currentRounds = in.readInt();
        currentThrows = in.readInt();
        gameFinished = in.readByte() != 0;
        newRound = in.readByte() != 0;
    }

    public static final Creator<Counter> CREATOR = new Creator<Counter>() {
        @Override
        public Counter createFromParcel(Parcel in) {
            return new Counter(in);
        }

        @Override
        public Counter[] newArray(int size) {
            return new Counter[size];
        }
    };

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
        else if(currentThrows > totalThrows){
            resetThrow();
        }
    }

    public void resetThrow(){
        currentThrows = 0;
        newRound= false;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public boolean isItANewRound() {
        return newRound;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(totalThrows);
        parcel.writeInt(totalRounds);
        parcel.writeInt(currentRounds);
        parcel.writeInt(currentThrows);
        parcel.writeByte((byte) (gameFinished ? 1 : 0));
        parcel.writeByte((byte) (newRound ? 1 : 0));
    }
}

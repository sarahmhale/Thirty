package com.example.sarahhale.thirty.playlogic;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Dice implements Parcelable {
    private ArrayList<Die> dice = new ArrayList();

    public Dice (int nrOfDice){
        for (int i = 0; i < nrOfDice; i++){
            dice.add(new Die());
        }
    }

    protected Dice(Parcel in) {
    }

    public static final Creator<Dice> CREATOR = new Creator<Dice>() {
        @Override
        public Dice createFromParcel(Parcel in) {
            return new Dice(in);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };

    public ArrayList<Die> getDice() {
        return dice;
    }

    public void setDieToInactive(int position) {
        dice.get(position).setInactive();

    }

    public void rollAllDice() {
        for (Die die : dice){
            die.roll();
        }

    }

    public void setActive(int position) {
        dice.get(position).setActive();
    }

    public void setAllToActive() {
        for (Die die : dice){
            die.setActive();
        }
    }

    public ArrayList<Integer> getDiceValues() {
        ArrayList<Integer> diceValues = new ArrayList();

        for (Die die : dice){
            diceValues.add(die.getValue());
        }
        return diceValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    //TODO: setAllDiceToActive
}

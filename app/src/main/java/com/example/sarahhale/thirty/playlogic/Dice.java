package com.example.sarahhale.thirty.playlogic;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ParcelCreator")
public class Dice implements Parcelable {
    private List<Die> dice = new ArrayList();

    public Dice (int nrOfDice){
        for (int i = 0; i < nrOfDice; i++){
            dice.add(new Die());
        }
    }

    protected Dice(Parcel in) {
        dice = in.createTypedArrayList(Die.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(dice);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public List<Die> getDice() {
        return dice;
    }

    public void setDieToInactive(int position) {
        dice.get(position).setActive(false);
    }

    public void rollAllDice() {
        for (Die die : dice){
            die.roll();
        }
    }

    public void setActive(int position) {
        dice.get(position).setActive(true);
    }

    public void setAllToActive() {
        for (Die die : dice){
            die.setActive(true);
        }
    }
}

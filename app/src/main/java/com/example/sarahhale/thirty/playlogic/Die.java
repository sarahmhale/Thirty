package com.example.sarahhale.thirty.playlogic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Random;

public class Die implements Comparable,Parcelable {
    private boolean active = true;
    private int value;
    private final Random random = new Random();
    private boolean reserved = false;
    private boolean usedForScore = false;


    public Die(){
        value =random.nextInt(6) + 1;
    }
    
    public Die(int value) {
        this.value= value;
    }

    protected Die(Parcel in) {
        active = in.readByte() != 0;
        value = in.readInt();
        reserved = in.readByte() != 0;
    }

    public static final Creator<Die> CREATOR = new Creator<Die>() {
        @Override
        public Die createFromParcel(Parcel in) {
            return new Die(in);
        }

        @Override
        public Die[] newArray(int size) {
            return new Die[size];
        }
    };


    public boolean isUsedForScore() {
        return usedForScore;
    }

    public void setUsedForScore(boolean usedForScore) {
        this.usedForScore = usedForScore;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int roll() {
        setReserved(false);
        if(active) {
            value = random.nextInt(6) + 1;
            return value;
        }
        return value;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getValue() {
        return value;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public int compareTo(@NonNull Object compareDie) {
        int compareValue=((Die)compareDie).getValue();
        return this.value-compareValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (active ? 1 : 0));
        parcel.writeInt(value);
        parcel.writeByte((byte) (reserved ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Die{" +
                "value=" + value +
                '}';
    }
}

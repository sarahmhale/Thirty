package com.example.sarahhale.thirty.playlogic;

import java.util.ArrayList;


public class Dice {
    private ArrayList<Die> dice = new ArrayList();

    public Dice (int nrOfDice){
        for (int i = 0; i < nrOfDice; i++){
            dice.add(new Die());
        }
    }
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
}

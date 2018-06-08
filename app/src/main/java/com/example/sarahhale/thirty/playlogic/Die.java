package com.example.sarahhale.thirty.playlogic;

import java.util.Random;

class Die {
    private boolean active = true;
    private int value;
    private Random random = new Random();

    public Die(){
        value =random.nextInt(6) + 1;
    }

    public int roll() {
        if(active) {
            value = random.nextInt(6) + 1;
            return value;
        }
        return value;
    }

    public void setInactive() {
        active= false;
    }

    public void setActive() {
        active= true;
    }

    public int getValue() {
        return value;
    }

    public boolean isActive() {
        return active;
    }
}

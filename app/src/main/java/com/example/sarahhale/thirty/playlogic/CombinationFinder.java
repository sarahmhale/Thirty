package com.example.sarahhale.thirty.playlogic;

import org.apache.commons.math3.util.Combinations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CombinationFinder {


    private List<Die> removeReserved (List<Die> dice){
        for(Iterator diceIterator = dice.iterator();
            diceIterator.hasNext();) {
            Die die = (Die)diceIterator.next();
            if(die.isReserved()){
                diceIterator.remove();
            }
        }
        return dice;
    }

    public List<Die> findAndRemove( List<Die> dice, int target) {
        for (int i = 1; i <=6; i++ ) {
            dice = findAndRemove(dice, target, i);
        }
        return dice;
    }

    public List<Die> findAndRemove( List<Die> dice, int target,int sizeOfSubset) {

        if(sizeOfSubset > dice.size()){
            return dice;
        }
        //finds all the ways you can combine the dice
        Combinations combinations = new Combinations(dice.size(),sizeOfSubset);
        Iterator<int[]> i = combinations.iterator();
        while (i.hasNext()){
            int[] combination = i.next();
            int sum = 0;
            for (int index: combination) {
                Die die = dice.get(index);
                //If the dice is already used, skip and set sum t0o zerp
                if (die.isReserved()) {
                    sum = 0;
                    break;
                }
                sum += die.getValue();
            }

            //if sum equals target, set all the dice that are included in the combination to used
            if (sum == target) {
                for (int index: combination) {
                    dice.get(index).setReserved(true);
                }
            }
        }
        removeReserved(dice);
        return dice;
    }

}

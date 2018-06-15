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

    public List<Die> findAndRemove( List<Die> dice, int target,int k) {

        if(k > dice.size()){
            return dice;
        }

        Combinations c = new Combinations(dice.size(),k);
        Iterator<int[]> i = c.iterator();
        while (i.hasNext()){
            int[] a = i.next();
            int sum = 0;
            for (int b: a) {
                Die die = dice.get(b);
                if (die.isReserved()) {
                    sum = 0;
                    break;
                }
                sum += die.getValue();
            }
            if (sum == target) {
                for (int b: a) {
                    dice.get(b).setReserved(true);
                }
            }
        }
        removeReserved(dice);
        return dice;
    }

}

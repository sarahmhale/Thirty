package com.example.sarahhale.thirty.playlogic;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CombinationFinderTest {

    private CombinationFinder combinationFinder = new CombinationFinder();

    private List<Die> createDiceArray(Integer[] diceValues){
        List<Die> dice = new ArrayList();
        for(int value : diceValues){
            dice.add(new Die(value));
        }
        return dice;
    }

    @Test
    public void shouldFindAllCombinationsThatSumUpToTarget() {
        List<Die> diceValues = createDiceArray(new Integer[]{4,2,4,2});
        List<Die> result = combinationFinder.findAndRemove(diceValues,4);
        List<Die> compareArray = createDiceArray(new Integer[]{});
        assertEquals(compareArray, result);
    }

    @Test
    public void shouldFindAllNumbersThatAreEqualToTarget() {
        List<Die> diceValues = createDiceArray(new Integer[]{4,1,4});
        List<Die> result = combinationFinder.findAndRemove(diceValues,4, 1);
        List<Die> compareArray = createDiceArray(new Integer[]{1});
        for(int i = 0; i < result.size(); i++ ){
            assertEquals(compareArray.get(i).getValue(), result.get(i).getValue());
        }
    }

    @Test
    public void shouldOnlyUseDiceOnce() {
        List<Die> diceValues = createDiceArray(new Integer[]{4,1,4});
        List<Die> result = combinationFinder.findAndRemove(diceValues,5, 2);
        List<Die> compareArray = createDiceArray(new Integer[]{4});
        for(int i = 0; i < result.size(); i++ ){
            assertEquals(compareArray.get(i).getValue(), result.get(i).getValue());
        }
    }
}

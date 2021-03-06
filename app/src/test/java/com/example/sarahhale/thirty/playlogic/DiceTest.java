package com.example.sarahhale.thirty.playlogic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DiceTest {
    private final Dice dice = new Dice(6);

    @Test
    public void shouldReturnAListWithSixDice (){
        List<Die> allDice = dice.getDice();
        assertEquals(6, allDice.size());
    }

    @Test
    public void theListShouldBeFilledWithDiceWithAValue (){
        List<Die> allDice = dice.getDice();
        List<Integer> diceValues = new ArrayList<>();

        for (Die die: allDice){
            diceValues.add(die.getValue());
        }

        assertEquals(6,diceValues.size());
    }

    @Test
    public void rollAllDice (){
        List<Die> result1 = dice.getDice();
        List<Integer> values1 = new ArrayList<>();

        for (Die die: result1){
            values1.add(die.getValue());
        }

        dice.rollAllDice();
        List<Die> result2 = dice.getDice();
        List<Integer> values2 = new ArrayList<>();


        for (Die die: result2){
            values2.add(die.getValue());
        }

        assertNotEquals(values2,values1);

    }

    @Test
    public void setDieToInactive (){
        List<Die> result1 = dice.getDice();
        List<Integer> values1 = new ArrayList<>();

        for (Die die: result1){
            values1.add(die.getValue());
        }

        dice.setDieToInactive(1);
        dice.rollAllDice();

       for (int i = 0; i< 3; i++){
           assertTrue(dice.getDice().get(1).getValue() == values1.get(1));
       }

    }

    @Test
    public void afterBeingSetInActiveAndThenActiveAgainAllDiceShouldRoll(){

        dice.setDieToInactive(1);
        dice.setActive(1);

        Set<Integer> dieValues = new HashSet();

        for (int i = 0; i < 6; i++){
            dice.rollAllDice();
            dieValues.add(dice.getDice().get(1).getValue());
        }

        assertTrue(dieValues.size() > 1);
    }

    @Test
    public void shouldSetAllDiceToActive (){

        dice.setDieToInactive(1);

        dice.setAllToActive();

        Set<Integer> dieValues = new HashSet();

        for (int i = 0; i < 6; i++){
            dice.rollAllDice();
            dieValues.add(dice.getDice().get(1).getValue());
        }
        assertTrue(dieValues.size() > 1);
    }

    @Test
    public void shouldReturnArrayListWithValuesOfDice(){
        List<Die> diceValue = dice.getDice();
        assertEquals(6, diceValue.size());
    }
}

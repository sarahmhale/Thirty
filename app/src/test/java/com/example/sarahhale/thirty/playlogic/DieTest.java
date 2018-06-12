package com.example.sarahhale.thirty.playlogic;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class DieTest {
    private final Die die = new Die();

    @Test
    public void rollDieShouldResultInRightRange() {
        for (int i = 0; i < 20; i++){
            int result = die.roll();
            assertTrue( result <= 6);
            assertTrue( result >= 1);

        }
    }

    @Test
    public void getValueOfDice(){
        int result = die.roll();
        int result2 = die.getValue();
        assertTrue(result == result2);
    }

    @Test
    public void repeatedRollsShouldNotAllBeSame(){
        Set<Integer> dieValues = new HashSet();

        for (int i = 0; i < 6; i++){
            dieValues.add(die.roll());
        }

        assertTrue(dieValues.size() > 1);
    }

    @Test
    public void rollDieShouldBeEvenlyDistributed(){

        Map<Integer,Integer> dieValues  = new HashMap();

        for (int i = 0; i < 10000; i++){
            int result =die.roll();
            if(dieValues.containsKey(result)){
               int value =  dieValues.get(result);
               dieValues.put(result,value+1);
            }else{
                dieValues.put(result,1);
            }
        }

        for (int i = 1; i <= 5; i++){
            assertTrue(dieValues.get(i) <= (dieValues.get(i+1)+200));
            assertTrue(dieValues.get(i) >= (dieValues.get(i+1)-200));
        }
    }

    @Test
    public void whenInactiveDieShouldNotRoll (){
        int result = die.roll();
        die.setActive(false);
        int result2 = die.roll();
        assertTrue(result == result2);

    }

    @Test
    public void afterBeingSetFromInactiveToActiveDiceShouldRoll() {
        int result = die.roll();
        die.setActive(false);
        int result2 = die.roll();
        die.setActive(true);

        Set<Integer> dieValues = new HashSet();

        for (int i = 0; i < 6; i++){
            dieValues.add(die.roll());
        }

        assertTrue(result == result2);
        assertTrue(dieValues.size() > 1);

    }

    @Test
    public void dieShouldBeActivePerDefault (){
        Die newDie = new Die();
        boolean active = newDie.isActive();
        assertTrue(active);
    }


    @Test
    public void dieShouldNotBeReservedPerDefault(){
        Die newDie = new Die();
        assertFalse( newDie.isReserved());
    }

    @Test
    public void afterRollDiceShouldBeUnreserved(){
        Die newDie = new Die();
        newDie.setReserved(true);
        newDie.roll();
        assertFalse( newDie.isReserved());
    }

}
package com.example.sarahhale.thirty.playlogic;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class CounterTest {
    private final Counter counter = new Counter(3,5);

    @Test
    public void whenCreatedRoundsShouldBeZero(){
        Counter counter1 = new Counter(2,6);
        int rounds =counter1.getRounds();
        assertEquals(0,rounds);
    }

    @Test
    public void whenCreatedThrowsShouldBeZero(){
        Counter counter1 = new Counter(1,3);
        int rounds =counter1.getThrows();
        assertEquals(0,rounds);
    }

    @Test
    public void onRoundShouldBeAddedToTotalRounds (){
        int result1 = counter.getRounds();
        counter.addRound();
        int result2 = counter.getRounds();
        assertNotEquals(result1,result2);
    }

    @Test
    public void onThrowShouldBeAddedToTotalThrows (){
        int result1 = counter.getThrows();
        counter.addThrow();
        int result2 = counter.getThrows();
        assertNotEquals(result1,result2);
    }

    @Test
    public void ifTotalThrowsIsReachedIncreaseRoundsWithOne(){
        Counter counter1 = new Counter(10,2);

        counter1.addThrow();
        counter1.addThrow();
        counter1.addThrow();

        assertEquals(0,counter1.getThrows());
        assertEquals(0,counter1.getRounds());

    }

    @Test
    public void ifTotalRoundsIsReachedThenReset(){
        Counter counter1 = new Counter(2,2);

        counter1.addRound();
        counter1.addRound();

        assertEquals(0,counter1.getThrows());
        assertEquals(0,counter1.getRounds());

    }

    @Test
    public void shouldReturnTrueIfGameIsFinished(){
        Counter counter1 = new Counter(2,2);

        counter1.addRound();
        counter1.addRound();

        assertTrue(counter1.isGameFinished());
    }

    @Test
    public void shouldReturnFalseIfGameIsNotFinished(){
        Counter counter1 = new Counter(2,2);

        counter1.addRound();

        assertFalse(counter1.isGameFinished());
    }
}

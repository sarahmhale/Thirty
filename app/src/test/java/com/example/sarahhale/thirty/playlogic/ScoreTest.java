package com.example.sarahhale.thirty.playlogic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
public class ScoreTest {

    private final Score score = new Score();

    private List<Die> createDiceArray(Integer[] diceValues){
        List<Die> dice = new ArrayList();
        for(int value : diceValues){
            dice.add(new Die(value));
        }
        return dice;
    }

    @Test
    public void oneValueShouldResultInThatScore() {
        List<Die> diceValues = createDiceArray(new Integer[]{3});
        int result = score.findBestCombinations(diceValues, "3");
        assertEquals(3, result);
    }

    @Test
    public void ifNoMatchItShouldReturn0() {
        List<Die> diceValues = createDiceArray(new Integer[]{4,5});
        int result = score.findBestCombinations(diceValues, "10");
        assertEquals(0, result);
    }

   @Test
    public void ifTwoValuesEqualTheScoreValueItShouldReturnTheScoreValue() {
        List<Die> diceValues = createDiceArray(new Integer[]{3,3});
        int result = score.findBestCombinations(diceValues, "6");
        assertEquals(6, result);
    }

    @Test
    public void ifSeveralValuesEqualTheScoreValueItShouldReturnTheSumOfAllTheMatches() {
        List<Die> diceValues = createDiceArray(new Integer[]{4,3,1});
        int result = score.findBestCombinations(diceValues, "4");
        assertEquals(8, result);
    }

    @Test
    public void ifSeveralValuesEqualTheScoreValueItShouldReturnTheSumOfAllTheMatches2() {
        List<Die> diceValues = createDiceArray(new Integer[]{4,3,2,2});
        int result = score.findBestCombinations(diceValues, "8");
        assertEquals(8, result);
    }

    @Test
    public void ifThreeValuesEqualTargetReturnTheTargetSum() {
        List<Die> diceValues = createDiceArray(new Integer[]{4,3,1});
        int result = score.findBestCombinations(diceValues, "8");
        assertEquals(8, result);
    }

    @Test
    public void allCombinationsOfTargetSumShouldBeReturned() {
        List<Die> diceValues = createDiceArray(new Integer[]{1,1,1});
        int result = score.findBestCombinations(diceValues, "2");
        assertEquals(2, result);
    }

    @Test
    public void findThreeCombinations() {
        List<Die> diceValues = createDiceArray(new Integer[]{2,2,2,2,2,2});
        int result = score.findBestCombinations(diceValues, "4");
        assertEquals(12, result);
    }

    @Test
    public void shouldBeAbleToCountTargetSumIfValueIsNotInTheRightOrder() {
        List<Die> diceValues = createDiceArray(new Integer[]{1,5,4});
        int result = score.findBestCombinations(diceValues, "10");
        assertEquals(10, result);
    }

    @Test
    public void shouldReturnRightValueWithSixDice() {
        List<Die> diceValues = createDiceArray(new Integer[]{6,6,3,2,2,1});
        int result = score.findBestCombinations(diceValues, "12");
        assertEquals(12, result);
    }

    @Test
    public void shouldReturnZeroWhenFirstCalledTotalScore() {
        int result = score.getTotalScore();
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnTheTotalScoreWhenScoreHasBeenSet() {
        score.addToTotalScore(10);
        int result = score.getTotalScore();
        assertEquals(10, result);
    }

    @Test
    public void shouldReturnTheRightTotalScoreAfterBeingSetTwice() {
        score.addToTotalScore(10);
        score.addToTotalScore(10);
        int result = score.getTotalScore();
        assertEquals(20, result);
    }

    @Test
    public void resetTotalScore() {
        score.addToTotalScore(10);
        score.reset();
        int result = score.getTotalScore();
        assertEquals(0, result);
    }

    @Test
    public void returnTheSumOfValuesLessorEqualToThree() {
        List<Die> diceValues = createDiceArray(new Integer[]{6,6,3,2,2,1});
        int result = score.low(diceValues);
        assertEquals(8, result);
    }


    @Test
    public void shouldReturnAllTheScoreAlternatives(){
        String[] testAlternatives = new String[]{"low","4","5","6","7","8","9","10","11","12"};
        String[] scoreAlternatives = score.getScoreAlternatives();


        for(int i = 0; i < testAlternatives.length; i++){
            assertEquals(scoreAlternatives[i],testAlternatives[i]);
        }

    }

    @Test
    public void afterScoreAlternativeHasBeenUsedItShouldNotBeReturned (){
        List<Die> diceValues = createDiceArray(new Integer[]{6,6,3,2,2,1});

        score.findBestCombinations(diceValues,"4");
        String[] testAlternatives = new String[]{"low","5","6","7","8","9","10","11","12"};
        String[] scoreAlternatives = score.getScoreAlternatives();


        for(int i = 0; i < testAlternatives.length; i++){
            assertEquals(scoreAlternatives[i],testAlternatives[i]);
        }

    }

    @Test
    public void afterLowHasBeenUsedItShouldNotBeReturnedAsAnAlternative(){
        Score testScore = new Score();
        List<Die> diceValues = createDiceArray(new Integer[]{6,6});

        testScore.low(diceValues);

        String[] testAlternatives = new String[]{"4","5","6","7","8","9","10","11","12"};
        String[] scoreAlternatives = testScore.getScoreAlternatives();

        for(int i = 0; i < testAlternatives.length; i++){
            assertEquals(scoreAlternatives[i],testAlternatives[i]);
        }

    }

    @Test
    public void shouldResetScoreAlternatives(){

        List<Die> diceValues = createDiceArray(new Integer[]{6,6});
        score.findBestCombinations(diceValues,"4");
        score.reset();

        String[] testAlternatives = new String[]{"low","4","5","6","7","8","9","10","11","12"};
        String[] scoreAlternatives = score.getScoreAlternatives();


        for(int i = 0; i < testAlternatives.length; i++){
            assertEquals(scoreAlternatives[i],testAlternatives[i]);
        }
    }
}

package com.example.sarahhale.thirty.playlogic;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class ScoreTest {

    Score score = new Score();

    @Test
    public void oneValueShouldResultInThatScore() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(3);
        int result = score.findBestCombinations(diceValues, 3);
        assertEquals(3, result);
    }

    @Test
    public void ifTwoValuesEqualTheScoreValueItShouldReturnTheScoreValue() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(3);
        diceValues.add(3);
        int result = score.findBestCombinations(diceValues, 6);
        assertEquals(6, result);
    }

    @Test
    public void ifSeveralValuesEqualTheScoreValueItShouldReturnTheSumOfAllTheMatches() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(4);
        diceValues.add(3);
        diceValues.add(1);
        int result = score.findBestCombinations(diceValues, 4);
        assertEquals(8, result);
    }

    @Test
    public void ifThreeValuesEqualTargetReturnTheTargetSum() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(4);
        diceValues.add(3);
        diceValues.add(1);
        int result = score.findBestCombinations(diceValues, 8);
        assertEquals(8, result);
    }

    @Test
    public void allCombinationsOfTargetSumShouldBeReturned() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(1);
        diceValues.add(1);
        diceValues.add(1);
        int result = score.findBestCombinations(diceValues, 2);
        assertEquals(2, result);
    }

    @Test
    public void findThreeCombinations() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(2);
        diceValues.add(2);
        diceValues.add(2);
        diceValues.add(2);
        diceValues.add(2);
        diceValues.add(2);
        int result = score.findBestCombinations(diceValues, 4);
        assertEquals(12, result);
    }

    @Test
    public void shouldBeAbleToCountTargetSumIfValueIsNotInTheRightOrder() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(1);
        diceValues.add(5);
        diceValues.add(4);
        int result = score.findBestCombinations(diceValues, 10);
        assertEquals(10, result);
    }

    @Test
    public void shouldReturnRightValueWithSixDice() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(6);
        diceValues.add(6);
        diceValues.add(3);
        diceValues.add(2);
        diceValues.add(2);
        diceValues.add(1);
        int result = score.findBestCombinations(diceValues, 12);
        assertEquals(12, result);
    }

    @Test
    public void shouldReturnZeroWhenFirstCalledTotalScore() {
        ArrayList<Integer> diceValues = new ArrayList();

        int result = score.totalScore();
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnTheTotalScoreWhenScoreHasBeenSet() {
        ArrayList<Integer> diceValues = new ArrayList();
        score.addToTotalScore(10);
        int result = score.totalScore();
        assertEquals(10, result);
    }

    @Test
    public void shouldReturnTheRightTotalScoreAfterBeingSetTwice() {
        ArrayList<Integer> diceValues = new ArrayList();
        score.addToTotalScore(10);
        score.addToTotalScore(10);
        int result = score.totalScore();
        assertEquals(20, result);
    }

    @Test
    public void resetTotalScore() {
        ArrayList<Integer> diceValues = new ArrayList();
        score.addToTotalScore(10);
        score.reset();
        int result = score.totalScore();
        assertEquals(0, result);
    }

    @Test
    public void returnTheSumOfValuesLessorEqualToThree() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(6);
        diceValues.add(6);
        diceValues.add(3);
        diceValues.add(2);
        diceValues.add(2);
        diceValues.add(1);
        int result = score.low(diceValues);
        assertEquals(8, result);
    }


    @Test
    public void returnTheSumOFValuesLessorEqualToThreeWithToList() {
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(6);
        diceValues.add(6);
        diceValues.add(3);
        diceValues.add(2);
        diceValues.add(2);
        diceValues.add(1);
        int result = score.low(diceValues);
        assertEquals(8, result);

        diceValues.clear();

        diceValues.add(1);
        diceValues.add(6);
        diceValues.add(3);
        diceValues.add(2);
        diceValues.add(2);
        diceValues.add(1);
        result = score.low(diceValues);
        assertEquals(9, result);
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
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(6);
        diceValues.add(6);
        diceValues.add(3);
        diceValues.add(2);
        diceValues.add(2);
        diceValues.add(1);
        score.findBestCombinations(diceValues,4);
        String[] testAlternatives = new String[]{"low","5","6","7","8","9","10","11","12"};
        String[] scoreAlternatives = score.getScoreAlternatives();


        for(int i = 0; i < testAlternatives.length; i++){
            assertEquals(scoreAlternatives[i],testAlternatives[i]);
        }

    }

    @Test
    public void afterLowHasBeenUsedItShouldNotBeReturnedAsAnAlternative(){
        Score testScore = new Score();
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(6);
        diceValues.add(6);
        testScore.low(diceValues);

        String[] testAlternatives = new String[]{"4","5","6","7","8","9","10","11","12"};
        String[] scoreAlternatives = testScore.getScoreAlternatives();

        for(int i = 0; i < testAlternatives.length; i++){
            assertEquals(scoreAlternatives[i],testAlternatives[i]);
        }

    }

    @Test
    public void shouldResetScoreAlternatives(){
        ArrayList<Integer> diceValues = new ArrayList();
        diceValues.add(6);
        diceValues.add(6);
        score.findBestCombinations(diceValues,4);
        score.reset();

        String[] testAlternatives = new String[]{"low","4","5","6","7","8","9","10","11","12"};
        String[] scoreAlternatives = score.getScoreAlternatives();


        for(int i = 0; i < testAlternatives.length; i++){
            assertEquals(scoreAlternatives[i],testAlternatives[i]);
        }
    }

}

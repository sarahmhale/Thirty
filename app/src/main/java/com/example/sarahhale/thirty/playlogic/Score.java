package com.example.sarahhale.thirty.playlogic;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Score implements Parcelable{
    private String[] SCORE_ALTERNATIVES = new String[]{"low","4","5","6","7","8","9","10","11","12"};
    private int totalScore;
    private String[] scoreAlternatives;
    private final List<String> scoreForEachRound = new ArrayList<>();
    private CombinationFinder combinationFinder = new CombinationFinder();


    public Score(){
        scoreAlternatives = SCORE_ALTERNATIVES;
    }

    protected Score(Parcel in) {
        SCORE_ALTERNATIVES = in.createStringArray();
        totalScore = in.readInt();
        scoreAlternatives = in.createStringArray();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    private void removeScoreAlternative(String value){
        List<String> remainingScoreAlternatives = new ArrayList(Arrays.asList(scoreAlternatives));
        remainingScoreAlternatives.remove(value);
        scoreAlternatives = remainingScoreAlternatives.toArray(new String[0]);
    }

    /*
    * Compares the original dice list to the result list. In the result list the combinations that
    * are possible are removed. Sums upp the values that are not included in the result list and
    * returns the sum.
    * */
    private int getSum(List<Die> diceValues, List<Die> result) {
        int sum = 0;
        List<Die> dice = new ArrayList(diceValues);
        dice.removeAll(result);

        for(Die die: dice){
            sum += die.getValue();
        }

        return sum;
    }

    public int getBestScore(List<Die> diceValues, String value) {
        removeScoreAlternative(value);

        if (value.equals("low")) {
            return low(diceValues);
        } else {
            int target = Integer.parseInt(value);
            List<Die> dice = new ArrayList(diceValues);
            return getSum(diceValues, combinationFinder.findAndRemove(dice, target));
        }
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addToTotalScore(int score) { totalScore += score; }

    public void reset() {
        scoreAlternatives = SCORE_ALTERNATIVES;
        totalScore= 0;
    }

    public int low(List<Die> diceValues) {

        removeScoreAlternative("low");

        int result = 0;
        for (Die die : diceValues){
            if(die.getValue() <= 3){
                result += die.getValue();
            }
        }
        return result;
    }

    public String[] getScoreAlternatives() {
        return scoreAlternatives;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(SCORE_ALTERNATIVES);
        parcel.writeInt(totalScore);
        parcel.writeStringArray(scoreAlternatives);
    }

    public void setTheScoreForRound(String key, Integer score) {
        scoreForEachRound.add(key+": "+score.toString()+ "p");
    }

    public List<String> getScoreForRounds() { return scoreForEachRound; }
}

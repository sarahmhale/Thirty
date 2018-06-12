package com.example.sarahhale.thirty.playlogic;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class Score implements Parcelable{
    private String[] SCORE_ALTERNATIVES = new String[]{"low","4","5","6","7","8","9","10","11","12"};
    private int totalScore;
    private String[] scoreAlternatives;
    private final List<String> scoreForEachRound = new ArrayList<>();


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

    public int findBestCombinations(List<Die> diceValues, String value) {
        removeScoreAlternative(value);
        int target = Integer.parseInt(value);

        Collections.sort(diceValues);
        Collections.reverse(diceValues);

        List<Die> dice = new ArrayList(diceValues);


        int result = sumUp(dice,target,target);

        return result*target;
    }

    private List<Die> removeReserved (List<Die> diceValues){
        for (int i = 0; i< diceValues.size();i++){
            if(diceValues.get(i).isReserved()){
                diceValues.remove(i);
            }
        }
        return diceValues;
    }

    private int sumUp(List<Die> diceValues, int target,int startTarget){
        int count = 0;

        for (int i = 0; i < diceValues.size(); i++) {
            Die die =diceValues.get(i);

            if(!die.isReserved()){
                die.setReserved(true);

                if (die.getValue() == target) {
                    count++;
                    diceValues = removeReserved(diceValues);
                    count += sumUp(diceValues, startTarget,startTarget);
                }else if (die.getValue() < target){
                    target = target - die.getValue();
                    count += sumUp(diceValues, target,startTarget);
                }else {
                    die.setReserved(false);
                }
            }
        }

        return count;
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

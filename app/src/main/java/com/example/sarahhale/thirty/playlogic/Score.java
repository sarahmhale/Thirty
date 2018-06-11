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

    public void removeScoreAlternative(String value){

        List<String> list = new ArrayList<String>(Arrays.asList(scoreAlternatives));
        list.remove(value);
        scoreAlternatives = list.toArray(new String[0]);
    }

    public int findBestCombinations(ArrayList<Integer> diceValues, String value) {

        removeScoreAlternative(value);
        int target = Integer.parseInt(value);

        Collections.sort(diceValues);
        int result = 0;

        if(diceValues.size()>1){

            for(int i = 0; i< diceValues.size();i++){
                if(diceValues.get(i)==target){
                    result +=target;
                    diceValues.remove(i);
                }else{
                    int sum = diceValues.get(i);
                    for (int j = i+1; j<diceValues.size(); j++){
                        sum += diceValues.get(j);
                        if(sum == target) {
                            result += target;
                            diceValues.remove(j);

                        }
                    }
                }
            }

        }else if(diceValues.get(0)== target){
            return target;
        }
        return result;
    }

    public int totalScore() {
        return totalScore;
    }

    public void addToTotalScore(int score) {
        totalScore += score;

    }

    public void reset() {
        scoreAlternatives = SCORE_ALTERNATIVES;
        totalScore= 0;
    }

    public int low(ArrayList<Integer> diceValues) {

        removeScoreAlternative("low");

        Collections.sort(diceValues);
        int result = 0;
        for (int i : diceValues){
           if(i > 3){
               return result;
           }else{
               result += i;
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
}

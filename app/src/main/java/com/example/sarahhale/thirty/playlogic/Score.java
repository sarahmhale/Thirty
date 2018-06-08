package com.example.sarahhale.thirty.playlogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


class Score {

    private int totalScore;


    public int findBestCombinations(ArrayList<Integer> diceValues, int target) {

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
        totalScore= 0;
    }

    public int low(ArrayList<Integer> diceValues) {

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
}

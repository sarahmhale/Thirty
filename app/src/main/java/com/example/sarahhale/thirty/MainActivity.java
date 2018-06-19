package com.example.sarahhale.thirty;
import com.example.sarahhale.thirty.playlogic.*;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Dice dice;
    private GridView gridView;
    private Button rollButton;
    private Button newRoundButton;
    private Counter counter;
    private Score score;
    private Spinner spinner;

    private final int ROUNDS = 10;
    private final int THROWS = 3;
    private final String DICE = "DICE";
    private final String SCORE = "SCORE";
    private final String COUNTER = "COUNTER";


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            setUpNewVariables();
        }else{
            restoreFromState(savedInstanceState);
        }

        renderScoreAlternativeSpinner();
        renderDice();
        setThrowText();
        setRoundText();
    }

    private void restoreFromState(Bundle savedInstanceState){
        dice = savedInstanceState.getParcelable(DICE);
        score= savedInstanceState.getParcelable(SCORE);
        counter = savedInstanceState.getParcelable(COUNTER);
        findAllUIComponents();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SCORE, score);
        outState.putParcelable(DICE, dice);
        outState.putParcelable(COUNTER, counter);
    }

    private void setUpNewVariables(){
        counter = new Counter(ROUNDS,THROWS);
        dice = new Dice(6);
        score = new Score();
        findAllUIComponents();
    }

    private void findAllUIComponents(){
        gridView = findViewById(R.id.gridview);
        rollButton = findViewById(R.id.rollButton);
        newRoundButton= findViewById(R.id.newRoundButton);
        spinner = findViewById(R.id.spinner);
        newRoundButton.setVisibility(View.GONE);
    }

    private void renderDice (){

        final DiceAdapter diceAdapter =new DiceAdapter(this,dice);
        gridView.setAdapter(diceAdapter);
        System.out.println("eee"+ counter.getThrows());


        /*
          Change image to either active or inactive after being clicked.
          */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(counter.getThrows() != 0) {
                    if (dice.getDice().get(position).isActive()) {
                        dice.getDice().get(position).setActive(false);
                    } else {
                        dice.getDice().get(position).setActive(true);
                    }
                    ((ImageView) v.findViewById(R.id.dice_image)).setImageResource(
                            DiceAdapter.setImage(
                                    dice.getDice().get(position).getValue(),
                                    dice.getDice().get(position).isActive()
                            ));
                }
            }
        });
    }


    private void renderScoreAlternativeSpinner(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, score.getScoreAlternatives());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void setThrowText(){
        TextView currentThrowsText = findViewById(R.id.current_throws);
        int currentThrows = counter.getThrows();
        currentThrowsText.setText("Throws "+ currentThrows+"/"+(THROWS));
    }

    private void setRoundText(){
        TextView currentRoundsText = findViewById(R.id.current_rounds);
        int currentRounds =counter.getRounds();
        currentRoundsText.setText("Rounds "+(currentRounds+1)+"/"+ROUNDS);
    }

    private void updateScore (String target){
        int result = score.getBestScore(dice.getDice(),target);
        score.addToTotalScore(result);
        score.setTheScoreForRound(target,result);
        renderScoreAlternativeSpinner();
    }

    private void startResultActivity(){
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra(SCORE, score.getTotalScore());
        intent.putStringArrayListExtra("SCORE_PER_ROUNDS",(ArrayList<String>) score.getScoreForRounds());
        finish();
        startActivity(intent);
    }

    /*
    * Checks if the game is finished, if not the score is updated, the dice are rolled and the
    * button is changed from "NEW ROUND" to "ROLL".
    * */
    public void newRound(View view){
        counter.addRound();
        updateScore(spinner.getSelectedItem().toString());

        if(counter.isGameFinished()){
           startResultActivity();
        }else {
            counter.resetThrow();
            setThrowText();
            setRoundText();
            dice.setAllToActive();
            renderDice();
            rollButton.setVisibility(View.VISIBLE);
            newRoundButton.setVisibility(View.GONE);
        }
    }

    /*
    * Adds a throw and rolls the dice. If it is new round the button is changed from "ROLL" to
    * "NEW ROUND" */
    public void rollDice(View view){
        counter.addThrow();
        setThrowText();

        if(counter.isItANewRound()){
            rollButton.setVisibility(View.GONE);
            newRoundButton.setVisibility(View.VISIBLE);
        }

        dice.rollAllDice();
        renderDice();
    }
}


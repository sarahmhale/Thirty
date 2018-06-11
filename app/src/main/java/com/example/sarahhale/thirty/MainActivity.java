package com.example.sarahhale.thirty;
import com.example.sarahhale.thirty.playlogic.*;

import android.content.Intent;
import android.os.PersistableBundle;
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

import java.io.Serializable;
import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {
    private Dice dice;
    GridView gridView;
    Button rollButton;
    Button newRoundButton;
    ImageAdapter imageAdapter;
    Counter counter;
    Score score;
    Spinner spinner;
    ArrayAdapter<String> dataAdapter;

    private final int ROUNDS = 10;
    private final int THROWS = 2;
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
        renderScore();
        renderDice();
        setThrowText();
        setRoundText();
    }

    public void restoreFromState(Bundle savedInstanceState){
        dice = savedInstanceState.getParcelable(DICE);
        score= savedInstanceState.getParcelable(SCORE);
        counter = savedInstanceState.getParcelable(COUNTER);
        findAllComponents();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SCORE, score);
        outState.putParcelable(DICE, dice);
        outState.putParcelable(COUNTER, counter);
    }

    public void setUpNewVariables(){
        counter = new Counter(ROUNDS,THROWS);
        dice = new Dice(6);
        score = new Score();
        findAllComponents();
    }

    public void findAllComponents(){
        gridView = (GridView) findViewById(R.id.gridview);
        rollButton = (Button) findViewById(R.id.rollButton);
        newRoundButton= findViewById(R.id.newRoundButton);
        spinner = (Spinner) findViewById(R.id.spinner);
        newRoundButton.setVisibility(View.GONE);
    }

    public void renderDice (){
        imageAdapter =new ImageAdapter(this,dice);
        gridView.setAdapter(imageAdapter);

        /**
         * Change image after image being clicked.
         * */

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if(dice.getDice().get(position).isActive()){
                    dice.getDice().get(position).setInactive();
                }else{
                    dice.getDice().get(position).setActive();
                }
                ((ImageView) v).setImageResource(
                        ImageAdapter.setImage(
                                dice.getDice().get(position).getValue(),
                                dice.getDice().get(position).isActive()
                        ));

            }
        });

    }

    public void renderScore(){
        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, score.getScoreAlternatives());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    public void setThrowText(){
        TextView currentThrowsText = findViewById(R.id.current_throws);
        int currentThrows = counter.getThrows();
        currentThrowsText.setText("Throws "+ currentThrows+"/"+THROWS);
    }

    public void setRoundText(){
        TextView currentRoundsText = findViewById(R.id.current_rounds);
        int currentRounds =counter.getRounds();
        currentRoundsText.setText("Rounds "+currentRounds+"/"+ROUNDS);
    }

    public void updateScore (String target){
        int result = 0;
        if(target == "low"){
            result= score.low(dice.getDiceValues());

        }else{
            result= score.findBestCombinations(dice.getDiceValues(),target);
        }
        score.addToTotalScore(result);
        score.setTheScoreForRound(target,result);
        renderScore();
    }

    public void startResultActivity(){
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra(SCORE, score.totalScore());
        intent.putStringArrayListExtra("SCORE_PER_ROUNDS", score.getScoreForRounds());
        startActivity(intent);
    }

    public void newRound(View view){
        dice.rollAllDice();
        counter.addRound();
        updateScore(spinner.getSelectedItem().toString());

        if(counter.isGameFinished()){
            startResultActivity();
        }
        else {
            counter.addThrow();
            setThrowText();
            setRoundText();
            dice.setAllToActive();
            imageAdapter.notifyDataSetChanged();
            rollButton.setVisibility(View.VISIBLE);
            newRoundButton.setVisibility(View.GONE);
        }
    }

    public void rollDice(View view){
        counter.addThrow();
        setThrowText();
        setRoundText();

        if(counter.isItANewRound()){
            rollButton.setVisibility(View.GONE);
            newRoundButton.setVisibility(View.VISIBLE);
        }

        dice.rollAllDice();
        imageAdapter.notifyDataSetChanged();
    }

}


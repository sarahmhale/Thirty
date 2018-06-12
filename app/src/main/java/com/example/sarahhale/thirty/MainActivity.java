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
    private ImageAdapter imageAdapter;
    private Counter counter;
    private Score score;
    private Spinner spinner;

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

    private void restoreFromState(Bundle savedInstanceState){
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

    private void setUpNewVariables(){
        counter = new Counter(ROUNDS,THROWS);
        dice = new Dice(6);
        score = new Score();
        findAllComponents();
    }

    private void findAllComponents(){
        gridView = findViewById(R.id.gridview);
        rollButton = findViewById(R.id.rollButton);
        newRoundButton= findViewById(R.id.newRoundButton);
        spinner = findViewById(R.id.spinner);
        newRoundButton.setVisibility(View.GONE);
    }

    private void renderDice (){
        imageAdapter =new ImageAdapter(this,dice);
        gridView.setAdapter(imageAdapter);

        /*
          Change image after image being clicked.
          */

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if(dice.getDice().get(position).isActive()){
                    dice.getDice().get(position).setActive(false);
                }else{
                    dice.getDice().get(position).setActive(true);
                }
                ((ImageView) v).setImageResource(
                        ImageAdapter.setImage(
                                dice.getDice().get(position).getValue(),
                                dice.getDice().get(position).isActive()
                        ));

            }
        });

    }

    private void renderScore(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, score.getScoreAlternatives());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    private void setThrowText(){
        TextView currentThrowsText = findViewById(R.id.current_throws);
        int currentThrows = counter.getThrows();
        currentThrowsText.setText("Throws "+ currentThrows+"/"+THROWS);
    }

    private void setRoundText(){
        TextView currentRoundsText = findViewById(R.id.current_rounds);
        int currentRounds =counter.getRounds();
        currentRoundsText.setText("Rounds "+currentRounds+"/"+ROUNDS);
    }

    private void updateScore (String target){
        int result;
        if(target.equals("low")){
            result= score.low(dice.getDice());

        }else{
            result = score.findBestCombinations(dice.getDice(),target);
        }
        score.addToTotalScore(result);
        score.setTheScoreForRound(target,result);
        renderScore();
    }

    private void startResultActivity(){
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra(SCORE, score.getTotalScore());
        intent.putStringArrayListExtra("SCORE_PER_ROUNDS",(ArrayList<String>) score.getScoreForRounds());
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


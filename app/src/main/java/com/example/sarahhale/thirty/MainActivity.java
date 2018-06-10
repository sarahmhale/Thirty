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
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = new Counter(10,3);
        dice = new Dice(6);
        score = new Score();
        gridView = (GridView) findViewById(R.id.gridview);
        rollButton = (Button) findViewById(R.id.rollButton);
        newRoundButton= findViewById(R.id.newRoundButton);
        newRoundButton.setVisibility(View.GONE);
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

    public void addThrow(View view){
        counter.addThrow();
        TextView currentThrowsText = findViewById(R.id.current_throws);
        TextView currentRoundsText = findViewById(R.id.current_rounds);

        int currentThrows = counter.getThrows();
        int currentRounds =counter.getRounds();

        currentRoundsText.setText("Rounds "+currentRounds+"/10");
        currentThrowsText.setText("Throws "+ currentThrows+"/3");
    }

    public void newRound(View view){
        counter.addRound();

        if(counter.isGameFinished()){
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("Score", score.totalScore());
            startActivity(intent);
            System.out.println("game is finished show result");
        }else {
            addThrow(view);
            dice.setAllToActive();
            imageAdapter.notifyDataSetChanged();
            rollButton.setVisibility(View.VISIBLE);
            newRoundButton.setVisibility(View.GONE);
        }
    }

    public void rollDice(View view){
        addThrow(view);

        if(counter.isItANewRound()){
            rollButton.setVisibility(View.GONE);
            newRoundButton.setVisibility(View.VISIBLE);
        }

        dice.rollAllDice();
        imageAdapter.notifyDataSetChanged();
    }

}


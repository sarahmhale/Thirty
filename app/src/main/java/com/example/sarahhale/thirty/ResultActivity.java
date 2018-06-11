package com.example.sarahhale.thirty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    private ArrayList<String> scorePerRounds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
        scorePerRounds = intent.getStringArrayListExtra("SCORE_PER_ROUNDS");


        TextView textView = findViewById(R.id.totalScore);
        textView.setText("Total score: " + intent.getIntExtra("SCORE",0));
        

        ListView list = (ListView) findViewById(R.id.scorePerRound);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                scorePerRounds );

        list.setAdapter(arrayAdapter);

    }

  /*  private void populateListView(HashMap scorePerRounds) {

    }*/


    public void restart (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

package com.example.sarahhale.thirty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private ArrayList<String> scorePerRounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        scorePerRounds = intent.getStringArrayListExtra("SCORE_PER_ROUNDS");

        renderTotalScore( intent.getIntExtra("SCORE",0));
        populateScorePerRounds();
    }

    private void renderTotalScore(int score) {
        TextView textView = findViewById(R.id.totalScore);
        textView.setText("Total score: " +score);
    }

    private void populateScorePerRounds() {
        ListView list = findViewById(R.id.scorePerRound);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                scorePerRounds);

        list.setAdapter(arrayAdapter);
    }

    public void restart(View view){
        Intent intent = new Intent(this,MainActivity.class);
        finish();
        startActivity(intent);
    }
}

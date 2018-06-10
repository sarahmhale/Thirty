package com.example.sarahhale.thirty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.score);
        System.out.println(intent.getIntExtra("SCORE",0));
        textView.setText("score: " + intent.getIntExtra("SCORE",0));
    }

    public void restart (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

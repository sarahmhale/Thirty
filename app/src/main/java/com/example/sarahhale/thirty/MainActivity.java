package com.example.sarahhale.thirty;
import com.example.sarahhale.thirty.playlogic.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dice dice = new Dice(6);
        GridView gridView = (GridView) findViewById(R.id.gridview);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this,dice));

    }


}


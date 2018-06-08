package com.example.sarahhale.thirty;
import com.example.sarahhale.thirty.playlogic.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private Dice dice;
    GridView gridView;
    Button rollButton;
    ImageAdapter imageAdapter;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dice = new Dice(6);
        gridView = (GridView) findViewById(R.id.gridview);
        rollButton = (Button) findViewById(R.id.rollButton);
        imageAdapter =new ImageAdapter(this,dice);

        // Instance of ImageAdapter Class
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

    public void rollDice(View view){
        dice.rollAllDice();
        imageAdapter.notifyDataSetChanged();
    }

}


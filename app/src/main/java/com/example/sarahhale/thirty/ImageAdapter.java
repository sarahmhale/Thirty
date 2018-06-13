package com.example.sarahhale.thirty;
import com.example.sarahhale.thirty.playlogic.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

class ImageAdapter extends BaseAdapter {
    private final Context mContext;
    private final Dice dice;
    private LayoutInflater inflater;


    public ImageAdapter(Context c, Dice dice) {
        mContext = c;
        this.dice = dice;

    }

    public int getCount() {
        return dice.getDice().size();
    }

    public Object getItem(int position) {
        return  dice.getDice().get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dice_image,null);
        }
        ImageView die = convertView.findViewById(R.id.dice_image);
        die.setImageResource(setImage(dice.getDice().get(position).getValue(),dice.getDice().get(position).isActive() ));
        return die;
    }

   static public int setImage (int number,boolean active) {
        switch (number) {
            case 1:
                if(active) return R.drawable.white1;
                return  R.drawable.grey1;

            case 2:
                if(active) return R.drawable.white2;
                return  R.drawable.grey2;

            case 3:
                if(active) return R.drawable.white3;
                return  R.drawable.grey3;

            case 4:
                if(active) return R.drawable.white4;
                return  R.drawable.grey4;

            case 5:
                if(active) return R.drawable.white5;
                return  R.drawable.grey5;

            case 6:
                if(active) return R.drawable.white6;
                return  R.drawable.grey6;

            default:
                return  R.drawable.white6;

        }
    }

}
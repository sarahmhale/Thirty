package com.example.sarahhale.thirty;
import com.example.sarahhale.thirty.playlogic.*;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Dice dice;


    public ImageAdapter(Context c, Dice dice) {
        mContext = c;
        this.dice = dice;

    }

    public int getCount() {
        return 6;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(setImage(dice.getDice().get(position).getValue(),dice.getDice().get(position).isActive() ));
        return imageView;
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
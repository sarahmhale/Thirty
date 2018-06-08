package com.example.sarahhale.thirty;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Integer> dice;

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.white1, R.drawable.white2,
            R.drawable.white3, R.drawable.white4,
            R.drawable.white5, R.drawable.white6
    };

    public ImageAdapter(Context c, ArrayList<Integer> dice) {
        mContext = c;
        for (int i = 0; i <dice.size(); i++){
            mThumbIds[i] = setImage(dice.get(i));
        }
    }


    public int getCount() {
        return mThumbIds.length;
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
            imageView.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    public int setImage (int number) {
        switch (number) {
            case 1:
                return R.drawable.white1;

            case 2:
                return R.drawable.white2;

            case 3:
                return R.drawable.white3;

            case 4:
                return R.drawable.white4;

            case 5:
                return R.drawable.white5;

            case 6:
                return R.drawable.white6;

            default:
                return  R.drawable.white6;

        }
    }


}
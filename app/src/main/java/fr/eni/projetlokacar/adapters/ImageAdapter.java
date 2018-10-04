package fr.eni.projetlokacar.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetlokacar.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bitmap> images;

    public ImageAdapter(Context c) {
        mContext = c;
        images = new ArrayList<>();
    }

    public void addBitmap(Bitmap bitmap){
        images.add(bitmap);
    }

    public int getCount() {
        return images.size();
    }

    public Bitmap getItem(int position) {
        return images.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        CardView cardView;
        ImageView imageView;

        if (convertView == null) {
            cardView = new CardView(mContext);
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setElevation(5f);
            imageView.setAdjustViewBounds(true);
            cardView.addView(imageView, 0);
        } else {
            cardView = (CardView) convertView;
            imageView = (ImageView) cardView.getChildAt(0);
        }

        imageView.setImageBitmap(images.get(position));
        return cardView;
    }
    
}
package com.example.android.successquotepix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class ImageAdapter extends ArrayAdapter<String> {

    private String[] images;

    public ImageAdapter(Context context, String[] images) {
        super(context, 0, images);

        this.images = images;
    }

    public int getCount() {
        return images.length;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image, parent, false);
        }

        ImageView iv = (ImageView) convertView.findViewById(R.id.image);
        try {
            InputStream is = getContext().getAssets().open(MainActivity.THUMBS_PATH + "/" + images[position]);
            iv.setImageBitmap(BitmapFactory.decodeStream(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
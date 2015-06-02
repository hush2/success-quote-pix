package com.example.android.successquotepix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String THUMBS_PATH = "success/thumbs";
    public static final String IMAGE_POSITION = "IMAGE_POSITION";

    private String[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        images = getThumbImages();
        gridview.setAdapter(new ImageAdapter(this, images));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                intent.putExtra(IMAGE_POSITION, position);
                startActivity(intent);
            }
        });
    }

    String[] getThumbImages() {
        try {
            return getAssets().list(THUMBS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

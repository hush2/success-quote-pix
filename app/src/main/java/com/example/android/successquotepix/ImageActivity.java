package com.example.android.successquotepix;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        int pos = getIntent().getIntExtra(MainActivity.IMAGE_POSITION, 0);

        ViewPager vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        vp.setCurrentItem(pos);

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            MyFragment fragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt(MainActivity.IMAGE_POSITION, position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 30;
        }
    }

    public static class MyFragment extends Fragment {

        public MyFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ImageView iv = (ImageView) rootView.findViewById(R.id.imageView);
            int pos = getArguments().getInt(MainActivity.IMAGE_POSITION);
            try {
                InputStream is = getActivity().getAssets().open(String.format("success/success%02d.jpg", pos));
                iv.setImageBitmap(BitmapFactory.decodeStream(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rootView;
        }
    }


}


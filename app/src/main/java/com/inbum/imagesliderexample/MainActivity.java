package com.inbum.imagesliderexample;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    DotIndicator sliderDotspanel;

    private List<String> imagesUrl = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagesUrl.add("https://via.placeholder.com/150.png");
        imagesUrl.add("https://via.placeholder.com/1000x150.png");
        imagesUrl.add("https://via.placeholder.com/150x300.png");
        imagesUrl.add("https://via.placeholder.com/500x300.png");

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, imagesUrl);
        viewPager.setAdapter(viewPagerAdapter);

        imagesUrl.add("https://via.placeholder.com/40.png");
        imagesUrl.add("https://via.placeholder.com/900.png");
        imagesUrl.add("https://via.placeholder.com/150.png");
        imagesUrl.add("https://via.placeholder.com/150.png");

        viewPagerAdapter.notifyDataSetChanged();

        sliderDotspanel = (DotIndicator) findViewById(R.id.SliderDots);
        sliderDotspanel.setViewpager(viewPager);

    }
}

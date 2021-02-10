package com.hong_studio.myauctionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    DotsIndicator dotsIndicator;
    ViewPager viewPager;
    ImageViewPagerAdapter adapter;

    ArrayList<Integer> imgIds= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        loadData();

        dotsIndicator = findViewById(R.id.dots_indicator);
        viewPager = findViewById(R.id.pager);
        adapter = new ImageViewPagerAdapter(this, imgIds);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
    }

    void loadData(){
        imgIds.add(R.drawable.img01);
        imgIds.add(R.drawable.img02);
        imgIds.add(R.drawable.img03);
    }
}
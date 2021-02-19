package com.hong_studio.myauctionapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.hong_studio.myauctionapp.ImageViewPagerAdapterForImageActivity;
import com.hong_studio.myauctionapp.PhotoViewPager;
import com.hong_studio.myauctionapp.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {

    ArrayList<Integer> imgIds= new ArrayList<>();
    DotsIndicator dotsIndicator;
    PhotoViewPager photoViewPager;
    ImageViewPagerAdapterForImageActivity adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        loadData();
        setPhotoViewPagerAndDotsIndicator();
    }

    private void loadData(){
        imgIds.add(R.drawable.img01);
        imgIds.add(R.drawable.img02);
        imgIds.add(R.drawable.img03);
    }

    private void setPhotoViewPagerAndDotsIndicator() {
        dotsIndicator = findViewById(R.id.dots_indicator);
        photoViewPager = findViewById(R.id.photoViewPager);
        adapter = new ImageViewPagerAdapterForImageActivity(this, imgIds);
        photoViewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(photoViewPager);

        int position= getIntent().getIntExtra("imgPosition", 0);
        photoViewPager.setCurrentItem(position);
    }
}
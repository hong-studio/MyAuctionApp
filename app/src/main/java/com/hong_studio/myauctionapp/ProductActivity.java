package com.hong_studio.myauctionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    ArrayList<Integer> imgIds= new ArrayList<>();
    DotsIndicator dotsIndicator;
    ViewPager viewPager;
    ImageViewPagerAdapter adapter;
    LinearLayout layoutProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        setToolbar();
        loadData();
        setViewPagerAndDotsIndicator();
        setLayoutProfile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        MaterialToolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void loadData(){
        imgIds.add(R.drawable.img01);
        imgIds.add(R.drawable.img02);
        imgIds.add(R.drawable.img03);
    }

    private void setViewPagerAndDotsIndicator() {
        dotsIndicator = findViewById(R.id.dots_indicator);
        viewPager = findViewById(R.id.pager);
        adapter = new ImageViewPagerAdapter(this, imgIds);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
    }

    private void setLayoutProfile() {
        layoutProfile= findViewById(R.id.layout_profile);
        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProductActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
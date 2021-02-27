package com.hong_studio.myauctionapp.Tab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.hong_studio.myauctionapp.R;

public class CustomerCenterActivity extends AppCompatActivity {

    ViewGroup tContainer;
    TextView tv01, tv02;
    ImageView iv01Down, iv02Down;
    TextView tv01whenDown, tv02whenDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_center);

        setToolbar();

        tContainer= findViewById(R.id.transitionContainer);
        tv01= findViewById(R.id.tv01);
        tv02= findViewById(R.id.tv02);
        iv01Down= findViewById(R.id.iv01_down);
        iv02Down= findViewById(R.id.iv02_down);
        tv01whenDown= findViewById(R.id.tv01_whenDown);
        tv02whenDown= findViewById(R.id.tv02_whenDown);

        onClickDownArrow();
    }

    private void onClickDownArrow() {
        iv01Down.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(tContainer);
                visible= !visible;
                tv01whenDown.setVisibility(visible ? View.VISIBLE: View.GONE);
            }
        });

        iv02Down.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(tContainer);
                visible= !visible;
                tv02whenDown.setVisibility(visible ? View.VISIBLE: View.GONE);
            }
        });
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
    }
}
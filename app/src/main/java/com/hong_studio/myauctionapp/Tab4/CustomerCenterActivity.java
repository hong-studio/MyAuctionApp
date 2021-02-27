package com.hong_studio.myauctionapp.Tab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.hong_studio.myauctionapp.R;

public class CustomerCenterActivity extends AppCompatActivity {

    TextView tv01, tv02;
    ImageView iv01Down, iv02Down;
    TextView tv01Down, tv02Down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_center);

        setToolbar();

        tv01= findViewById(R.id.tv01);
        tv02= findViewById(R.id.tv02);
        iv01Down= findViewById(R.id.iv01_down);
        iv02Down= findViewById(R.id.iv02_down);
        tv01Down= findViewById(R.id.tv01_down);
        tv02Down= findViewById(R.id.tv02_down);
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
package com.hong_studio.myauctionapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.hong_studio.myauctionapp.R;

public class CategoryActivity extends AppCompatActivity {

    CheckBox[] checkBoxArray= new CheckBox[14];
    String[] checkBoxText= new String[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setToolbar();

//        Intent intent= getIntent();
//        for (int i=0; i<checkBoxArray.length; i++){
//            checkBoxArray[i]= findViewById(R.id.checkbox1+i);
//            checkBoxText[i]= checkBoxArray[i].getText().toString();
//        }
//        for(int i=0; i<checkBoxArray.length; i++){
//            if (checkBoxArray[i].isChecked()) {
//                intent.putExtra("checkbox"+ (i+1), checkBoxText[i]);
//            }
//        }
//        this.setResult(RESULT_OK, intent);
    }

    private void setToolbar() {
        MaterialToolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
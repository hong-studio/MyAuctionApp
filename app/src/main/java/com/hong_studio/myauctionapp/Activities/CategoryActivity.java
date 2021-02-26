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
import com.google.gson.Gson;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.Tab1.Tab1Page1Fragment;

public class CategoryActivity extends AppCompatActivity {

//    CheckBox[] checkBoxArray= new CheckBox[14];
//    String[] checkBoxText= new String[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setToolbar();

//        for(int i=0; i<checkBoxArray.length; i++){
//            checkBoxArray[i]= findViewById(R.id.checkbox1+i);
//        }
//
//        //CheckBox의 체크상태가 변경되는 것을 듣는 리스너객체 생성
//        CompoundButton.OnCheckedChangeListener listener= new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                for(int i=0; i<checkBoxArray.length; i++){
//                    if(checkBoxArray[i].isChecked()==true){
//                        checkBoxText[i]= checkBoxArray[i].getText().toString();
//                    } else if(checkBoxArray[i].isChecked()==false){
//                        checkBoxText[i]= null;
//                    }
//                }
//            }
//        };
//
//        //체크박스에게 리스너 설정해주기
//        for(int i=0; i<checkBoxArray.length; i++){
//            checkBoxArray[i].setOnCheckedChangeListener(listener);
//        }
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
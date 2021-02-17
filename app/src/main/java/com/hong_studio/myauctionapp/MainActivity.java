package com.hong_studio.myauctionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hong_studio.myauctionapp.Tab1.Tab1Fragment;
import com.hong_studio.myauctionapp.Tab2.Tab2Fragment;
import com.hong_studio.myauctionapp.Tab3.Tab3Fragment;
import com.hong_studio.myauctionapp.Tab4.Tab4Fragment;
import com.hong_studio.myauctionapp.Tab5.Tab5Fragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment[] fragments= new Fragment[5];
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager= getSupportFragmentManager();
        FragmentTransaction tran= fragmentManager.beginTransaction();
        fragments[0]= new Tab1Fragment();
        tran.add(R.id.container, fragments[0]);
        tran.commit();

        bnv= findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran= fragmentManager.beginTransaction();
                if(fragments[0]!=null) tran.hide(fragments[0]);
                if(fragments[1]!=null) tran.hide(fragments[1]);
                if(fragments[2]!=null) tran.hide(fragments[2]);
                if(fragments[3]!=null) tran.hide(fragments[3]);
                if(fragments[4]!=null) tran.hide(fragments[4]);

                switch (item.getItemId()){
                    case R.id.bnv_tab1:
                        tran.show(fragments[0]);
                        break;

                    case R.id.bnv_tab2:
                        if(fragments[1]==null){
                            fragments[1]= new Tab2Fragment();
                            tran.add(R.id.container, fragments[1]);
                        }
                        tran.show(fragments[1]);
                        break;

                    case R.id.bnv_tab3:
                        if(fragments[2]==null){
                            fragments[2]= new Tab3Fragment();
                            tran.add(R.id.container, fragments[2]);
                        }
                        tran.show(fragments[2]);
                        break;

                    case R.id.bnv_tab4:
                        if(fragments[3]==null){
                            fragments[3]= new Tab4Fragment();
                            tran.add(R.id.container, fragments[3]);
                        }
                        tran.show(fragments[3]);
                        break;

                    case R.id.bnv_tab5:
                        if(fragments[4]==null){
                            fragments[4]= new Tab5Fragment();
                            tran.add(R.id.container, fragments[4]);
                        }
                        tran.show(fragments[4]);
                        break;
                }
                tran.commit();
                return true;
            }
        });
    }

    private long backBtnTime = 0;
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;
        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        }else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }
}

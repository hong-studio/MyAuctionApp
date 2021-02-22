package com.hong_studio.myauctionapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.hong_studio.myauctionapp.G;
import com.hong_studio.myauctionapp.Item;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.RetrofitHelper;
import com.hong_studio.myauctionapp.RetrofitService;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductActivity extends AppCompatActivity {

    ArrayList<Item> items= new ArrayList<>();

    LinearLayout layoutProfile;

    ImageView ivProductImg;
    CircleImageView ivProfileImg;
    TextView tvMemberName, tvProductName, tvCategory, tvMsg;
    ImageView ivFavor;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        setToolbar();
        loadData();

        //findViewByID...
        ivProductImg= findViewById(R.id.iv_productImg);
        ivProfileImg= findViewById(R.id.iv_profileImg);
        tvMemberName= findViewById(R.id.tv_memberName);
        tvProductName= findViewById(R.id.tv_productName);
        tvCategory= findViewById(R.id.tv_category);
        tvMsg= findViewById(R.id.tv_msg);
        tvTime= findViewById(R.id.tv_time);

        onClickProductImg();
        setLayoutProfile();

        onClickHeart();
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
        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<ArrayList<Item>> call= retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {

                Glide.with(ProductActivity.this).load(G.profileImgUrl).into(ivProfileImg);
                tvMemberName.setText(G.memberName);

                ArrayList<Item> list= response.body();
                for(Item item : list){
                    String productImgUrl= "http://hongstudio.dothome.co.kr/Retrofit/"+item.productImg;
                    Glide.with(ProductActivity.this).load(productImgUrl).into(ivProductImg);
                    tvProductName.setText(item.productName);
                    tvCategory.setText(item.category);
                    tvMsg.setText(item.msg);
                    tvTime.setText(item.time);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickProductImg() {
        ivProductImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProductActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
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

    private void onClickHeart() {
        ivFavor= findViewById(R.id.iv_favor);
        ivFavor.setOnClickListener(new View.OnClickListener() {
            int isClicked= 0;
            @Override
            public void onClick(View v) {
                if(isClicked==0){
                    Glide.with(ProductActivity.this).load(R.drawable.ic_heart_filled).into(ivFavor);
                    isClicked= 1;
                } else if(isClicked==1){
                    Glide.with(ProductActivity.this).load(R.drawable.ic_heart_border).into(ivFavor);
                    isClicked= 0;
                }
            }
        });
    }

}
package com.hong_studio.myauctionapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.hong_studio.myauctionapp.BottomSheetDialog;
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

        //findViewById...
        layoutProfile= findViewById(R.id.layout_profile);
        ivProductImg= findViewById(R.id.iv_productImg);
        ivProfileImg= findViewById(R.id.iv_profileImg);
        tvMemberName= findViewById(R.id.tv_memberName);
        tvProductName= findViewById(R.id.tv_productName);
        tvCategory= findViewById(R.id.tv_category);
        tvMsg= findViewById(R.id.tv_msg);
        tvTime= findViewById(R.id.tv_time);

        loadDataAndSetData();
        onClickProductImg();
        onClickLayoutProfile();

        onClickHeart();
    }

    private void loadDataAndSetData() {
        String jsonStr= getIntent().getStringExtra("item");
        Item item= new Gson().fromJson(jsonStr, Item.class);

        Glide.with(this).load(RetrofitHelper.baseUrlRetrofitFolder+item.productImg).into(ivProductImg);
        Glide.with(this).load(item.profileImg).into(ivProfileImg);
//        Log.i("productImg", item.productImg);
//        Log.i("profileImg",item.profileImg);
        tvMemberName.setText(item.memberName);
        tvProductName.setText(item.productName);
        tvCategory.setText(item.category);
        tvMsg.setText(item.msg);
        tvTime.setText(item.time);
    }

    private void onClickProductImg() {
        ivProductImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonStr= getIntent().getStringExtra("item");
                Item item= new Gson().fromJson(jsonStr, Item.class);

                Intent intent= new Intent(ProductActivity.this, ImageActivity.class);
                intent.putExtra("productImg", item.productImg);
                startActivity(intent);
            }
        });
    }

    private void onClickLayoutProfile() {
        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonStr= getIntent().getStringExtra("item");
                Item item= new Gson().fromJson(jsonStr, Item.class);

                Intent intent= new Intent(ProductActivity.this, ProfileActivity.class);
                intent.putExtra("profileImg", item.profileImg);
                intent.putExtra("memberName", item.memberName);
                startActivity(intent);
            }
        });
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
            case R.id.share_menu:
//                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.getInstance();
//                bottomSheetDialog.show(getSupportFragmentManager(),"bottomSheet");
                Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");
                String Test_Message = "공유할 Text";
                Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);
                Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                startActivity(Sharing);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        MaterialToolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void onClickHeart() {
        ivFavor= findViewById(R.id.iv_favor);
        ivFavor.setOnClickListener(new View.OnClickListener() {
            int isClicked= 0;
            @Override
            public void onClick(View v) {
                if(G.memberName!=null){
                    if(isClicked==0){
                        Glide.with(ProductActivity.this).load(R.drawable.ic_heart_filled).into(ivFavor);
                        isClicked= 1;
                    } else if(isClicked==1){
                        Glide.with(ProductActivity.this).load(R.drawable.ic_heart_border).into(ivFavor);
                        isClicked= 0;
                    }
                } else if(G.memberName==null){
                    startActivity(new Intent(ProductActivity.this, LoginActivity.class));
                    Toast.makeText(ProductActivity.this, "로그인이 필요합니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
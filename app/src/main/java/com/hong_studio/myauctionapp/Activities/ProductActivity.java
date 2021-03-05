package com.hong_studio.myauctionapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.hong_studio.myauctionapp.G;
import com.hong_studio.myauctionapp.Item;
import com.hong_studio.myauctionapp.KeyboardVisibilityUtils;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.RetrofitHelper;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ProductActivity extends AppCompatActivity {

    NestedScrollView scrollView;
//    LinearLayout layoutProfile;

    ImageView ivProductImg;
    CircleImageView ivProfileImg;
    TextView tvMemberName, tvProductName, tvCategory, tvMsg, tvPrice;
    ImageView ivFavor;
    TextView tvTime;
    EditText etPrice;

    KeyboardVisibilityUtils keyboardVisibilityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        setToolbar();

        //findViewById...
        scrollView= findViewById(R.id.scrollView);
//        layoutProfile= findViewById(R.id.layout_profile);
        ivProductImg= findViewById(R.id.iv_productImg);
        ivProfileImg= findViewById(R.id.iv_profileImg);
        tvMemberName= findViewById(R.id.tv_memberName);
        tvProductName= findViewById(R.id.tv_productName);
        tvCategory= findViewById(R.id.tv_category);
        tvMsg= findViewById(R.id.tv_msg);
        tvTime= findViewById(R.id.tv_time);
        tvPrice= findViewById(R.id.tv_price);
        etPrice= findViewById(R.id.et_price);

        loadDataAndSetData();
        onClickProductImg();
        onClickMemberName();
        onShowKeyboard();
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
        tvPrice.setText("현재가격 : "+item.price+"원");
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

    private void onClickMemberName() {
        tvMemberName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonStr= getIntent().getStringExtra("item");
                Item item= new Gson().fromJson(jsonStr, Item.class);

                Intent intent= new Intent(ProductActivity.this, ProfileActivity.class);
                intent.putExtra("profileImg", item.profileImg);
                intent.putExtra("memberName", item.memberName);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right_anim, R.anim.slide_out_left_anim);
            }
        });
    }

    private void onShowKeyboard() {
        keyboardVisibilityUtils= new KeyboardVisibilityUtils(getWindow(), new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer) {
                scrollView.smoothScrollTo(scrollView.getScrollX(), scrollView.getScrollY()+ integer);
                return null;
            }
        }, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_product, menu);
        MenuItem editItem= menu.findItem(R.id.edit_menu);

        String jsonStr= getIntent().getStringExtra("item");
        Item item= new Gson().fromJson(jsonStr, Item.class);

        if(G.memberName!=null && G.memberName.equals(item.memberName)) editItem.setVisible(true);
        else editItem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            case R.id.edit_menu:
                startActivity(new Intent(this, EditProductActivity.class));
                break;

            case R.id.share_menu:
//                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.getInstance();
//                bottomSheetDialog.show(getSupportFragmentManager(),"bottomSheet");
                Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");
                String Test_Message = "앱 다운로드 링크 보내도록 수정예정";
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

    public void clickPrice(View view) {

        String jsonStr= getIntent().getStringExtra("item");
        Item item= new Gson().fromJson(jsonStr, Item.class);
        
        if(etPrice.getText().toString().equals("")){
            Toast.makeText(this, "가격을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if(!etPrice.getText().toString().equals("")){
            if (Integer.parseInt(etPrice.getText().toString()) <= Integer.parseInt(item.price)){
                Toast.makeText(this, "현재가격보다 더 높은 가격을 입력해주세요", Toast.LENGTH_SHORT).show();
            } else if(Integer.parseInt(etPrice.getText().toString()) > Integer.parseInt(item.price)){
                item.price= etPrice.getText().toString();
                tvPrice.setText("현재가격 : "+item.price+"원");
                etPrice.setText("");
                Toast.makeText(this, "입찰이 완료되었습니다", Toast.LENGTH_SHORT).show();

                //데이터 업데이트....

                InputMethodManager imm= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
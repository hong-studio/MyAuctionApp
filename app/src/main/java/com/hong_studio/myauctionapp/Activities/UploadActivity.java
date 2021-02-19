package com.hong_studio.myauctionapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.hong_studio.myauctionapp.R;

public class UploadActivity extends AppCompatActivity {

    NestedScrollView scrollView;
    LinearLayout layoutCategory;
    String[] categories= new String[]{"디지털/가전", "가구/인테리어", "유아동/유아도서",
                                      "생활/가공식품", "스포츠/레저", "여성잡화",
                                      "여성의류", "남성패션/잡화", "게임/취미", "뷰티/미용",
                                      "반려동물용품", "도서/티켓/음반", "식물", "기타 상품"};
    TextView tvCategory;
    EditText etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        setToolbar();
        scrollView= findViewById(R.id.scrollView);
        layoutCategory= findViewById(R.id.layout_category);
        tvCategory= findViewById(R.id.tv_category);
        onClickCategory();

        etMsg= findViewById(R.id.et_msg);
        etMsgTouch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_upload, menu);
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
    }

    private void onClickCategory() {
        layoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UploadActivity.this).setItems(categories, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCategory.setText(categories[which]);
                    }
                }).create().show();
            }
        });
    }

    private void etMsgTouch() {
        etMsg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    scrollView.smoothScrollTo(0, scrollView.getScrollY() + etMsg.getHeight());
                }
                return false;
            }
        });
    }
}
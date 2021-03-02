package com.hong_studio.myauctionapp.Tab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.hong_studio.myauctionapp.R;

public class BankAccountActivity extends AppCompatActivity {

    RelativeLayout layoutAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account);

        setToolbar();
        layoutAccount= findViewById(R.id.layout_account);
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

    public void clickDeleteAccount(View view) {
        new AlertDialog.Builder(this).setMessage("선택된 계좌를 삭제하시겠습니까?").setNegativeButton("아니오", null)
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        layoutAccount.setVisibility(View.GONE);
                    }
                }).create().show();
    }

    public void clickAddAccount(View view) {
        new AlertDialog.Builder(this).setMessage("계좌 추가 기능 구현 예정입니다.").setPositiveButton("확인", null).create().show();
    }

}
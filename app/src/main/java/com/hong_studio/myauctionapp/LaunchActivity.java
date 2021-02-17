package com.hong_studio.myauctionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        animation();
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

    private void animation() {
        findViewById(R.id.appLogo).startAnimation(AnimationUtils.loadAnimation(this, R.anim.applogo_anim));
        findViewById(R.id.layout_loginBtn).startAnimation(AnimationUtils.loadAnimation(this, R.anim.loginbutton_anim));
    }

    public void clickKakaoLogin(View view) {
        LoginClient.getInstance().loginWithKakaoTalk(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null){
                    Toast.makeText(LaunchActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(LaunchActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                    //로그인한 계정정보 얻어오기
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            if(user != null){
                                long id= user.getId();
                                String nickName= user.getKakaoAccount().getProfile().getNickname();
                                String profileImageUrl= user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                                //다음에 접속할때 로그인 다시하지 않으려면 SharedPreference에 로그인정보를 저장해두고 불러오도록 코드 추가...
                                //카카오로그인데이터를 이용할일이 많기 때문에 Global이나 SharedPreference를 사용해서 데이터 전달...

                            }else {
                                Toast.makeText(LaunchActivity.this, "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show();
                            }
                            return null;
                        }
                    });

                }else {
                    Toast.makeText(LaunchActivity.this, "로그인 실패"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });
    }

    public void clickWithoutLogin(View view) {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
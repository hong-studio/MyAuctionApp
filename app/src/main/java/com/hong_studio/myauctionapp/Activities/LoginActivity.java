package com.hong_studio.myauctionapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hong_studio.myauctionapp.G;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.Tab1.Tab1Fragment;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clickKakaoLogin(View view) {
        LoginClient.getInstance().loginWithKakaoTalk(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null){
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
//                    Intent intent= new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
                    finish();

                    //로그인한 계정정보 얻어오기
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            if(user != null){
                                long id= user.getId();
                                G.memberName= user.getKakaoAccount().getProfile().getNickname();
                                G.profileImgUrl= user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                                //다음에 접속할때 로그인 다시하지 않으려면 SharedPreference에 로그인정보를 저장해두고 불러오도록 코드 추가...
                                //카카오로그인데이터를 이용할일이 많기 때문에 Global이나 SharedPreference를 사용해서 데이터 전달...

                            }else {
                                Toast.makeText(LoginActivity.this, "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show();
                            }
                            return null;
                        }
                    });

                }else {
                    Toast.makeText(LoginActivity.this, "로그인 실패"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });
    }
}
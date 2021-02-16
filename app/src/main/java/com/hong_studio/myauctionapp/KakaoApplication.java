package com.hong_studio.myauctionapp;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Kakao SDK 초기화
        KakaoSdk.init(this, "d3390624f3bba0f4f4ccaefd74d05552");
    }
}

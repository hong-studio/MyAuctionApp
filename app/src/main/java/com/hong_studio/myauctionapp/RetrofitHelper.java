package com.hong_studio.myauctionapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {
    public static String baseUrl= "http://hongstudio.dothome.co.kr/";
    public static String baseUrlRetrofitFolder= "http://hongstudio.dothome.co.kr/Retrofit/";

    public static Retrofit getRetrofitInstanceGson(){
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();

        return retrofit;
    }

    public static Retrofit getRetrofitInstanceScalars(){
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit= builder.build();

        return retrofit;
    }

}

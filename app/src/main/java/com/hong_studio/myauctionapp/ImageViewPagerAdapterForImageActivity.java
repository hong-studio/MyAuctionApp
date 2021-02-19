package com.hong_studio.myauctionapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class ImageViewPagerAdapterForImageActivity extends PagerAdapter {

    Context context;
    ArrayList<Integer> imgIds;

    public ImageViewPagerAdapterForImageActivity(Context context, ArrayList<Integer> imgIds) {
        this.context = context;
        this.imgIds = imgIds;
    }

    @Override
    public int getCount() {
        return imgIds.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        PhotoView photoView = new PhotoView(container.getContext());
        Glide.with(context).load(imgIds.get(position)).into(photoView);
        photoView.setMaximumScale(5.0F);
        photoView.setMediumScale(3.0F);
        container.addView(photoView);
        return photoView;
    }

    //ViewPager에서 제거해야 할 page를 제거하는 메소드
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //3번째 파라미터가 지워야 할 뷰 객체(page)
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}

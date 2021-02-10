package com.hong_studio.myauctionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ImageViewPagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Integer> imgIds;

    public ImageViewPagerAdapter(Context context, ArrayList<Integer> imgIds) {
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

        //뷰페이저가 보여줄 뷰(page)객체 생성
        LayoutInflater inflater= LayoutInflater.from(context);
        View page= inflater.inflate(R.layout.page_image, null);

        ImageView iv= page.findViewById(R.id.iv);
        iv.setImageResource(imgIds.get(position));

        //ListView는 만든 page를 리턴해 주었지만...
        //만들어낸 page를 ViewPager(container)에 추가해야함
        container.addView(page);

        //만든 페이지가 뷰가 맞는지 검사를 위해 리턴..
        //리턴해준 뷰 객체(page)를 isViewFromObject()메소드가 받음
        return page;
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

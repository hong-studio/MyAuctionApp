package com.hong_studio.myauctionapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageViewPagerAdapterForProductActivity extends PagerAdapter {

    Context context;
    ArrayList<Integer> imgIds;

    public ImageViewPagerAdapterForProductActivity(Context context, ArrayList<Integer> imgIds) {
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

        LayoutInflater inflater= LayoutInflater.from(context);
        View page= inflater.inflate(R.layout.page_image, null);

        ImageView iv= page.findViewById(R.id.iv);
        Glide.with(context).load(imgIds.get(position)).into(iv);

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ImageActivity.class);
                intent.putExtra("imgPosition", position);
                context.startActivity(intent);
            }
        });

        container.addView(page);
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

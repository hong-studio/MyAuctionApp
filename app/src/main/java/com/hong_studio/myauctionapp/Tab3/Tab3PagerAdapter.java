package com.hong_studio.myauctionapp.Tab3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Tab3PagerAdapter extends FragmentPagerAdapter {

    Fragment[] pages= new Fragment[2];
    String[] titles= new String[]{"등록한 상품", "입찰한 상품"};

    public Tab3PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        pages[0]= new Tab3Page1Fragment();
        pages[1]= new Tab3Page2Fragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return pages[position];
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

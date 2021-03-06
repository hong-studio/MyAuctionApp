package com.hong_studio.myauctionapp.Tab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.hong_studio.myauctionapp.Activities.CategoryActivity;
import com.hong_studio.myauctionapp.Activities.LoginActivity;
import com.hong_studio.myauctionapp.Activities.MainActivity;
import com.hong_studio.myauctionapp.Activities.NotificationActivity;
import com.hong_studio.myauctionapp.G;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.Activities.UploadActivity;

public class Tab1Fragment extends Fragment {

    MaterialToolbar toolbar;
    SearchView searchView;
    TabLayout tabLayout;

    ViewPager pager;
    Tab1PagerAdapter pagerAdapter;
    FloatingActionButton btnFAB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

//        ((Tab1Page1Fragment)pagerAdapter.pages[0]).loadData();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option_menu, menu);
        setSearchView(menu);
    }

    private void setSearchView(@NonNull Menu menu) {
        MenuItem menuItem= menu.findItem(R.id.search_menu);
        searchView= (SearchView) menuItem.getActionView();
        searchView.setQueryHint("검색어를 입력하세요");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //검색버튼(돋보기모양버튼)을 클릭했을 때..
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            //글씨가 변경될 때마다 발동하는 메소드
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_menu:

                break;

            case R.id.category_menu:
                startActivity(new Intent(getActivity(), CategoryActivity.class));
                getActivity().overridePendingTransition(0,0);
                break;

            case R.id.notification_menu:
                startActivity(new Intent(getActivity(), NotificationActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right_anim, R.anim.slide_out_left_anim);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //여기서 xml의 뷰들에 대한 find 참조.
        toolbar= view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        btnFAB= view.findViewById(R.id.btn_fab);
        onClickFAB();

        tabLayout= view.findViewById(R.id.layout_tab);
        pager= view.findViewById(R.id.pager);
        pagerAdapter= new Tab1PagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pager.setAdapter(pagerAdapter);

        //탭레이아웃과 뷰페이저를 연동
        tabLayout.setupWithViewPager(pager);
    }

    private void onClickFAB() {
        btnFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(G.memberName!=null){
                    Intent intent= new Intent(getActivity(), UploadActivity.class);
                    startActivity(intent);
                } else if(G.memberName==null){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    Toast.makeText(getActivity(), "로그인이 필요합니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package com.hong_studio.myauctionapp.Tab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hong_studio.myauctionapp.R;

import java.util.ArrayList;

public class Tab2Page2Fragment extends Fragment {

    ArrayList<Tab2RecyclerItem> items= new ArrayList<>();
    RecyclerView recyclerView;
    Tab2RecyclerAdapter recyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.page2_tab2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView= view.findViewById(R.id.recycler);
        recyclerAdapter= new Tab2RecyclerAdapter(getActivity(), items);
        recyclerView.setAdapter(recyclerAdapter);
    }

    void loadData(){
        //일단은 더미데이터
        items.add(new Tab2RecyclerItem(R.drawable.img04, "앱 기획서", "등록자 4", "10:29:32"));
        items.add(new Tab2RecyclerItem(R.drawable.img05, "커피잔", "등록자 5", "03:12:11"));
        items.add(new Tab2RecyclerItem(R.drawable.img06, "책", "등록자 6", "22:02:54"));
    }
}

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

public class Tab2Page1Fragment extends Fragment {

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
        return inflater.inflate(R.layout.page1_tab2, container, false);
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
        items.add(new Tab2RecyclerItem(R.drawable.img03, "컴퓨터", "등록자 3", "07:20:47"));
        items.add(new Tab2RecyclerItem(R.drawable.img02, "아기옷", "등록자 2", "20:53:15"));
        items.add(new Tab2RecyclerItem(R.drawable.img01, "스웨터", "등록자 1", "10:30:02"));
    }
}

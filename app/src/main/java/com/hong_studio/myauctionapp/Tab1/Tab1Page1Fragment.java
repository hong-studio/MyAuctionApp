package com.hong_studio.myauctionapp.Tab1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hong_studio.myauctionapp.Item;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.RetrofitHelper;
import com.hong_studio.myauctionapp.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Tab1Page1Fragment extends Fragment {

    ArrayList<Item> items= new ArrayList<>();
    RecyclerView recyclerView;
    Tab1Page1RecyclerAdapter recyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.page1_tab1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView= view.findViewById(R.id.recycler);
        recyclerAdapter= new Tab1Page1RecyclerAdapter(getActivity(), items);
        recyclerView.setAdapter(recyclerAdapter);
    }

    void loadData(){
        //일단은 더미데이터
//        items.add(new Tab1RecyclerItem(R.drawable.img01, "스웨터", "등록자 1", "10:30:02"));
//        items.add(new Tab1RecyclerItem(R.drawable.img02, "아기옷", "등록자 2", "20:53:15"));
//        items.add(new Tab1RecyclerItem(R.drawable.img03, "컴퓨터", "등록자 3", "07:20:47"));
        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<ArrayList<Item>> call= retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                items.clear();
                recyclerAdapter.notifyDataSetChanged();

                ArrayList<Item> list= response.body();
                for(Item item : list){
                    items.add(0, item);
                    recyclerAdapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                Toast.makeText(getActivity(), "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

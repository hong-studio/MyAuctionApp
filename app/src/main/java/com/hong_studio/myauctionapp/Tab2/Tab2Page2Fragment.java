package com.hong_studio.myauctionapp.Tab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hong_studio.myauctionapp.Item;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.RetrofitHelper;
import com.hong_studio.myauctionapp.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Tab2Page2Fragment extends Fragment {

    ArrayList<Item> items= new ArrayList<>();
    RecyclerView recyclerView;
    Tab2Page2RecyclerAdapter recyclerAdapter;
    SwipeRefreshLayout refreshLayout;

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
        recyclerAdapter= new Tab2Page2RecyclerAdapter(getActivity(), items);
        recyclerView.setAdapter(recyclerAdapter);

        refreshLayout= view.findViewById(R.id.layout_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    void loadData(){
        //일단은 더미데이터
//        items.add(new Tab2RecyclerItem(R.drawable.img04, "앱 기획서", "등록자 4", "10:29:32"));
//        items.add(new Tab2RecyclerItem(R.drawable.img05, "커피잔", "등록자 5", "03:12:11"));
//        items.add(new Tab2RecyclerItem(R.drawable.img06, "책", "등록자 6", "22:02:54"));
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

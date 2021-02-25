package com.hong_studio.myauctionapp.Tab3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hong_studio.myauctionapp.Activities.LoginActivity;
import com.hong_studio.myauctionapp.Activities.ProductActivity;
import com.hong_studio.myauctionapp.G;
import com.hong_studio.myauctionapp.Item;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.RetrofitHelper;
import com.hong_studio.myauctionapp.Tab2.Tab2Page1RecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Tab3Page1RecyclerAdapter extends RecyclerView.Adapter<Tab3Page1RecyclerAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public Tab3Page1RecyclerAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item_tab3page1, parent, false);
        VH vh= new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item= items.get(position);
        String imgUrl= RetrofitHelper.baseUrlRetrofitFolder +item.productImg;
        Glide.with(context).load(imgUrl).into(holder.ivProductImg);

        holder.tvProductName.setText(item.productName);
        holder.tvMemberName.setText(item.memberName);
        holder.tvTime.setText(item.time);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView ivProductImg;
        TextView tvProductName, tvMemberName, tvTime;

        public VH(@NonNull View itemView) {
            super(itemView);

            ivProductImg= itemView.findViewById(R.id.iv_productImg);
            tvProductName= itemView.findViewById(R.id.tv_productName);
            tvMemberName= itemView.findViewById(R.id.tv_memberName);
            tvTime= itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item item= items.get(getLayoutPosition());
                    Gson gson= new Gson();
                    String jsonStr= gson.toJson(item); //객체-->json문자열로 변환

                    Intent intent= new Intent(context, ProductActivity.class);
                    intent.putExtra("item", jsonStr);
                    context.startActivity(intent);
                }
            });
        }
    }
}

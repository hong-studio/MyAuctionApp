package com.hong_studio.myauctionapp.Tab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hong_studio.myauctionapp.Item;
import com.hong_studio.myauctionapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Tab1RecyclerAdapter extends RecyclerView.Adapter<Tab1RecyclerAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public Tab1RecyclerAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item_tab1page2, parent, false);
        VH vh= new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item= items.get(position);
        String imgUrl= "http://hongstudio.dothome.co.kr/Retrofit/"+item.productImg;
        Picasso.get().load(imgUrl).into(holder.ivProductImg);

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
        ImageView ivFavor;

        public VH(@NonNull View itemView) {
            super(itemView);

            ivProductImg= itemView.findViewById(R.id.iv_productImg);
            tvProductName= itemView.findViewById(R.id.tv_productName);
            tvMemberName= itemView.findViewById(R.id.tv_memberName);
            tvTime= itemView.findViewById(R.id.tv_time);
            ivFavor= itemView.findViewById(R.id.iv_favor);
        }
    }
}

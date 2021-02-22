package com.hong_studio.myauctionapp.Tab4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hong_studio.myauctionapp.Activities.ProductActivity;
import com.hong_studio.myauctionapp.R;

import java.util.ArrayList;

public class Tab4RecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Tab4RecyclerItem> items;

    public Tab4RecyclerAdapter(Context context, ArrayList<Tab4RecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.recycler_item2, parent, false);
        VH vh= new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;
        Tab4RecyclerItem item= items.get(position);

        Glide.with(context).load(item.imgUrl).into(vh.iv);
        vh.tvTitle.setText(item.title);
        vh.tvUser.setText(item.user);
        vh.tvTime.setText(item.time);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvTitle;
        TextView tvUser;
        TextView tvTime;
        ImageView ivHeart;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv);
            tvTitle= itemView.findViewById(R.id.tv_title);
            tvUser= itemView.findViewById(R.id.tv_user);
            tvTime= itemView.findViewById(R.id.tv_time);
//            ivHeart= itemView.findViewById(R.id.iv_heart);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, ProductActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}

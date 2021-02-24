package com.hong_studio.myauctionapp.Tab2;

import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Tab2Page2RecyclerAdapter extends RecyclerView.Adapter<Tab2Page2RecyclerAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public Tab2Page2RecyclerAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item_tab2page2, parent, false);
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

            ivFavor.setOnClickListener(new View.OnClickListener() {
                int isClicked= 0;
                @Override
                public void onClick(View v) {
                    if(G.memberName!=null){
                        if(isClicked==0){
                            Glide.with(context).load(R.drawable.ic_heart_filled).into(ivFavor);
                            isClicked= 1;
                        } else if(isClicked==1){
                            Glide.with(context).load(R.drawable.ic_heart_border).into(ivFavor);
                            isClicked= 0;
                        }
                    } else if(G.memberName==null){
                        context.startActivity(new Intent(context, LoginActivity.class));
                        Toast.makeText(context, "로그인이 필요합니다", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

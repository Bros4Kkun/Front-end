package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class workAdapter extends RecyclerView.Adapter<workAdapter.CustomViewHolder>{

    private ArrayList<workData> arrayList;
    public workAdapter(ArrayList<workData> arrayList) {this.arrayList = arrayList;}


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.iv_Cate.setImageResource(arrayList.get(position).getIv_Cate());
        holder.tv_Money.setText(arrayList.get(position).getTv_Money());
        holder.tv_Far.setText(arrayList.get(position).getTv_Far());
        holder.tv_Res.setText(arrayList.get(position).getTv_Res());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.tv_Money.getText().toString();
                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_Cate;
        protected TextView tv_Money;
        protected TextView tv_Far;
        protected TextView tv_Res;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_Cate = itemView.findViewById(R.id.iv_Cate);
            this.tv_Money = itemView.findViewById(R.id.tv_Money);
            this.tv_Far = itemView.findViewById(R.id.tv_Far);
            this.tv_Res = itemView.findViewById(R.id.tv_Res);

        }
    }
}

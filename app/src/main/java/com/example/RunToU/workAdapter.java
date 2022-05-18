package com.example.RunToU;

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

import java.util.ArrayList;


public class workAdapter extends RecyclerView.Adapter<workAdapter.CustomViewHolder>{

    //아이템 클릭 리스너 인터페이스
    interface OnItemClickListener{
        void onItemClick(View v, int position); //뷰와 포지션값
    }

    private OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

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
        holder.txtNum.setText("No."+arrayList.get(position).getTxtNum());

        holder.itemView.setTag(position);
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
        protected TextView txtNum;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_Cate = itemView.findViewById(R.id.iv_Cate);
            this.tv_Money = itemView.findViewById(R.id.tv_Money);
            this.tv_Far = itemView.findViewById(R.id.tv_Far);
            this.tv_Res = itemView.findViewById(R.id.tv_Res);
            this.txtNum = itemView.findViewById(R.id.txtNum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        if(onItemClickListener != null){
                            onItemClickListener.onItemClick(view,position);
                        }
                    }
                }
            });


        }
    }
}

package com.example.RunToU;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class reviewAdapter extends RecyclerView.Adapter<reviewAdapter.CustomViewHolder> {

    private ArrayList<reviewData> arrayList;
    public reviewAdapter(ArrayList<reviewData> arrayList) {this.arrayList = arrayList;}
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.iv_Profile.setImageResource(arrayList.get(position).getIv_Profile());
        holder.tv_Id.setText(arrayList.get(position).getTv_Id());
        holder.tv_Work.setText(arrayList.get(position).getTv_Work());
        holder.tv_Star.setText(arrayList.get(position).getTv_Star());
        holder.tv_Review.setText(arrayList.get(position).getTv_Review());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.tv_Id.getText().toString();
                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView iv_Profile;
        protected TextView tv_Id;
        protected TextView tv_Work;
        protected TextView tv_Star;
        protected TextView tv_Review;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_Profile = itemView.findViewById(R.id.iv_Profile);
            this.tv_Id = itemView.findViewById(R.id.tv_Id);
            this.tv_Work = itemView.findViewById(R.id.tv_Work);
            this.tv_Star = itemView.findViewById(R.id.tv_Star);
            this.tv_Review = itemView.findViewById(R.id.tv_Review);
        }
    }
}

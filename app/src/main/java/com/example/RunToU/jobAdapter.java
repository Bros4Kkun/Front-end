package com.example.RunToU;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class jobAdapter extends RecyclerView.Adapter<jobAdapter.CustomViewHolder> {

    private ArrayList<jobData> arrayList;
    public jobAdapter(ArrayList<jobData> arrayList){this.arrayList = arrayList;}
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job,parent);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.itemView.setTag(position);
        holder.tv_Job.setText(arrayList.get(position).getTv_Job());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int btnPosition = (int) view.getTag();
                arrayList.remove(btnPosition);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

            protected TextView tv_Job;
            protected ImageButton btnRemove;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_Job = itemView.findViewById(R.id.tv_Job);
            this.btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}

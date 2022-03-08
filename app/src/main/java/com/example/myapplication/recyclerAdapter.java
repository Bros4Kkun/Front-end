package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//https://dev-imaec.tistory.com/27 참조하여 쓴 코드임

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ItemViewHolder> {

    private ArrayList<veriData> listData;

    @NonNull
    @Override
    public recyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_verification, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.ItemViewHolder holder, int position) {
        //아이템을 하나하나보여주는 (bind되는) 함수이다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        //RecycleView의 총 개수이다.
        return listData.size();
    }

    void addItem(veriData data){
        //외부에서 item을 추가시킬 함수이다.
        listData.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView text1;
        private TextView text2;
        private ImageView imageView;

        ItemViewHolder(View itemView){
            super(itemView);

            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            imageView = itemView.findViewById(R.id.imageTest);
        }

        void onBind(veriData data){

        }
    }
}

package com.example.RunToU;

import android.content.Context;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Dataitem> myDatalist = null;

    public ChatAdpater(ArrayList<Dataitem> dataList) {myDatalist = dataList;}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType == Code.ViewType.CENTER_CONTENT) {
            view = inflater.inflate(R.layout.room_center_list_items, parent, false);
            return new CenterViewHolder(view);
        }else if(viewType == Code.ViewType.LEFT_CONTENT){
            view = inflater.inflate(R.layout.room_left_list_items,parent,false);
            return new LeftViewHolder(view);
        }else{
            view = inflater.inflate(R.layout.room_right_list_items,parent,false);
            return new RightViewHolder(view);

        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position){
        if(viewHolder instanceof CenterViewHolder){
            ((CenterViewHolder)viewHolder).textv.setText(myDatalist.get(position).getContent());
        }else if(viewHolder instanceof LeftViewHolder){
            ((LeftViewHolder)viewHolder).textv_nickname.setText(myDatalist.get(position).getName());
            ((LeftViewHolder)viewHolder).textv_msg.setText(myDatalist.get(position).getContent());
        }else{
            ((RightViewHolder)viewHolder).textv_msg.setText(myDatalist.get(position).getContent());
        }
    }

    @Override
    public int getItemCount(){
        return myDatalist.size();
    }

    @Override
    public int getItemViewType(int position){
        return myDatalist.get(position).getViewType();
    }
    public class CenterViewHolder extends RecyclerView.ViewHolder{
        TextView textv;
        public CenterViewHolder(@NonNull View itemView){
            super(itemView);
            textv=(TextView)itemView.findViewById(R.id.textv);
        }
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgv;
        TextView textv_nickname;
        TextView textv_msg;
        TextView textv_time;
        public LeftViewHolder(@NonNull View itemView){
            super(itemView);
            imgv = (CircleImageView)itemView.findViewById(R.id.imgv);
            textv_nickname=(TextView)itemView.findViewById(R.id.textv_nicname);
            textv_msg=(TextView)itemView.findViewById(R.id.textv_msg);
            textv_time=(TextView)itemView.findViewById(R.id.textv_time);
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{
        TextView textv_msg;
        TextView textv_time;
        public RightViewHolder(@NonNull View itemView){
            super(itemView);
            textv_msg =(TextView)itemView.findViewById(R.id.textv_msg);
            textv_time=(TextView)itemView.findViewById(R.id.textv_time);
        }
    }
}

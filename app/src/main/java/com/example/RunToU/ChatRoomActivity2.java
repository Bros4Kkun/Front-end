package com.example.RunToU;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ChatRoomActivity2 extends Activity {
    static ArrayList<Dataitem> datalist = new ArrayList<>();
    static int index=0;

    String chtmsg = new String();

    @Override
    protected void onCreate(@Nullable Bundle savedinstanceState){
        super.onCreate((savedinstanceState));
        setContentView(R.layout.room2);
        TextView chatmsg;
        ChatAdpater chatAdpater = new ChatAdpater(datalist);
        ImageButton but1;
        Button but2;
        initData();

        chatmsg = findViewById(R.id.editText1);
        but1 = findViewById(R.id.btn_send1);
        but2= findViewById(R.id.btn_check);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chtmsg = chatmsg.getText().toString();
                rightData(chtmsg);

                Stompclass.Stomclass.INSTANCE.send(chtmsg,94);


            }
        });
        RecyclerView recyvlerv= findViewById(R.id.recyvlerv);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyvlerv.setLayoutManager(manager);
        recyvlerv.setAdapter(chatAdpater);

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while(true){
                    if(index==1) {
                        refresh();
                        index = 0;
                    }}            }
        });







    }

    private void initData(){

        datalist.add(new Dataitem("채팅에 입장했습니다.",null,Code.ViewType.CENTER_CONTENT));
    }
    static void leftData(String msg,String nickname){
        datalist.add(new Dataitem(msg,nickname,Code.ViewType.LEFT_CONTENT));

    }
    private void rightData(String msg){
        datalist.add(new Dataitem(msg,null,Code.ViewType.RIGHT_CONTENT));

    }
    public final void notifyItemChanged(int position) {
    }
    public void refresh(){
        finish();
        overridePendingTransition(0,0);
        Intent intent = getIntent();
        startActivity(intent);
        overridePendingTransition(0,0);
    }

}
package com.example.RunToU;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import kotlin.Unit;

import static java.lang.Thread.sleep;

public class ChatRoomActivity2 extends Activity {
    static ArrayList<Dataitem> datalist = new ArrayList<>();
    static int index=0;
    static String chtmsg = new String();


       static String opponick = chatRoomAdapter.INSTANCE.getNicksend();




    static ChatAdpater chatAdpater = new ChatAdpater(opponick,datalist);

    static String msg1 = "";
    static String ID1 = "";
    @Override
    protected void onCreate(@Nullable Bundle savedinstanceState){
        super.onCreate((savedinstanceState));
        setContentView(R.layout.room2);
        TextView chatmsg;

        ImageButton but1;
        Button match;
        Button com;
        Button match_com;
        Button com_com;
        initData();

        chatmsg = findViewById(R.id.editText1);
        but1 = findViewById(R.id.btn_send1);
        match= findViewById(R.id.matchingok);
        match_com = findViewById(R.id.gomatching);
        com = findViewById(R.id.btn_check);
        com_com=findViewById(R.id.com);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chtmsg = chatmsg.getText().toString();
                chatmsg.setText("");
                rightData(chtmsg);

                Stompclass.Stomclass.INSTANCE.send(chtmsg,"/app/chat/chatroom/",chatRoomAdapter.INSTANCE.getChatindex());


            }
        });
        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchRequestVolley.matchRequestVolley.INSTANCE.matchRequestVolley(getApplicationContext(),chatRoomAdapter.INSTANCE.getChatindex());
                Stompclass.Stomclass.INSTANCE.subscribe("/topic/match/request/",chatRoomAdapter.INSTANCE.getMatchrein());
            }
        });
        match_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchsucVolley.matchsucVolley.INSTANCE.matchsucVolley(getParent(),chatRoomAdapter.INSTANCE.getMatchrein()); // 이부분 요청자 기준에서 수정
                Stompclass.Stomclass.INSTANCE.connect("topic/match/done/",chatRoomAdapter.INSTANCE.getMatchsucin());
            }
        });
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runComVolley.runComVolley.INSTANCE.runComVolley(getParent(),matchObjcet.INSTANCE.getMatchingPk());
            }
        });
        com_com.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runcomcomVolley.runcomcomVolley.INSTANCE.runcomcomVolley(getParent(),chatRoomAdapter.INSTANCE.getMatchsucin());
            }
        }));
        RecyclerView recyvlerv= findViewById(R.id.recyvlerv);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyvlerv.setLayoutManager(manager);
        recyvlerv.setAdapter(chatAdpater);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyvlerv.scrollToPosition(recyvlerv.getAdapter().getItemCount()-1);
            }
        }, 200);
    }




    private void initData(){
        datalist.clear();
        datalist.add(new Dataitem("채팅에 입장했습니다.",null,Code.ViewType.CENTER_CONTENT));
        chatlogVolley.chatlogVolley.INSTANCE.chatlogVolley(chatRoomAdapter.INSTANCE.getChatindex(),this);





    }
    static void leftData(String msg1,String nickname1){
        datalist.add(new Dataitem(msg1,nickname1,Code.ViewType.LEFT_CONTENT));
        chatAdpater.notifyDataSetChanged();
    }
    static void rightData(String msg){
        datalist.add(new Dataitem(msg,null,Code.ViewType.RIGHT_CONTENT));
        chatAdpater.notifyDataSetChanged();
    }

    static Boolean getMsg(String msg, String nickname){
        msg1=msg;
        ID1 = nickname;
        return true;
    }


}
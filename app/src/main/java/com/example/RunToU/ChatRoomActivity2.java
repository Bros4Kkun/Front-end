package com.example.RunToU;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRoomActivity2 extends Activity {
    private ArrayList<Dataitem> datalist;

    @Override
    protected void onCreate(@Nullable Bundle savedinstanceState){
        super.onCreate((savedinstanceState));
        setContentView(R.layout.room2);

        initData();

        RecyclerView recyvlerv= findViewById(R.id.recyvlerv);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyvlerv.setLayoutManager(manager);
        recyvlerv.setAdapter(new ChatAdpater(datalist));
    }

    private void initData(){
        datalist = new ArrayList<>();
        datalist.add(new Dataitem("사용자1님이 입장했습니다",null,Code.ViewType.CENTER_CONTENT));
        datalist.add(new Dataitem("사용자2님이 입장했습니다",null,Code.ViewType.CENTER_CONTENT));
        datalist.add(new Dataitem("ㅎㅇ요",null,Code.ViewType.LEFT_CONTENT));
        datalist.add(new Dataitem("ㅎㅇㅎㅇ",null,Code.ViewType.RIGHT_CONTENT));

    }
}

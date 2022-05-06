package com.example.RunToU;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class ChatRoomActivity2 extends Activity {
    private ArrayList<Dataitem> datalist = new ArrayList<>();

    String chtmsg = new String();
    String inputmsg = new String();
    @Override
    protected void onCreate(@Nullable Bundle savedinstanceState){
        super.onCreate((savedinstanceState));
        setContentView(R.layout.room2);
        TextView chatmsg;

        ImageButton but1;
        initData();

        chatmsg = findViewById(R.id.editText1);
        but1 = findViewById(R.id.btn_send1);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chtmsg = chatmsg.getText().toString();

                Stompclass.Stomclass.INSTANCE.connect(chtmsg);
                rightData(chtmsg);
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                
            }
        });
        RecyclerView recyvlerv= findViewById(R.id.recyvlerv);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyvlerv.setLayoutManager(manager);
        recyvlerv.setAdapter(new ChatAdpater(datalist));
    }

    private void initData(){

        datalist.add(new Dataitem("에코 채팅 서버에 입장했습니다.",null,Code.ViewType.CENTER_CONTENT));
    }
    private void leftData(String msg){
        datalist.add(new Dataitem(msg,null,Code.ViewType.LEFT_CONTENT));
    }
    private void rightData(String msg){
        datalist.add(new Dataitem(msg,null,Code.ViewType.RIGHT_CONTENT));
    }
}

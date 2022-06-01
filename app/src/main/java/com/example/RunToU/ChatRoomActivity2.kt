package com.example.RunToU

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.RunToU.Stompclass.Stomclass.send
import com.example.RunToU.Stompclass.Stomclass.subscribe
import com.example.RunToU.chatRoomAdapter.chatindex
import com.example.RunToU.chatRoomAdapter.matchrein
import com.example.RunToU.chatRoomAdapter.matchsucin
import com.example.RunToU.chatRoomAdapter.nicksend
import com.example.RunToU.chatRoomAdapter.requester
import com.example.RunToU.chatlogVolley.chatlogVolley.chatlogVolley
import com.example.RunToU.matchObjcet.matchingPk
import java.util.*


class ChatRoomActivity2 : Activity() {
    var num // 채팅방
            = 0
    var num2 // 매칭리ㅞ스트pk
            = 0
    var num3 // 매칭pk
            = 0
    var num4 = 0
    var num5 = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedinstanceState: Bundle?) {
        super.onCreate(savedinstanceState)
        setContentView(R.layout.room2)
        val chatmsg: TextView
        val but1: ImageButton
        val match: Button
        val com: Button
        val match_com: Button
        val com_com: Button
        val recyvlerv: RecyclerView = findViewById(R.id.recyvlerv)
        initData()
        num = chatindex
        if (requester) {
            subscribe("/topic/match/chatroom/", num)
        } else {
        }
        chatmsg = findViewById(R.id.editText1)
        but1 = findViewById(R.id.btn_send1)
        match = findViewById(R.id.matchingok)
        match_com = findViewById(R.id.gomatching)
        com = findViewById(R.id.btn_check)
        com_com = findViewById(R.id.com)
        but1.setOnClickListener {
            ChatRoomActivity2.Companion.chtmsg = chatmsg.text.toString()
            chatmsg.text = ""
            
            send(
                ChatRoomActivity2.Companion.chtmsg,
                "/app/chat/chatroom/",
                num
            )
            Handler().postDelayed(
                { recyvlerv.scrollToPosition(recyvlerv.adapter!!.itemCount - 1) },
                200
            )
        }
        match.setOnClickListener { // 수행자
            matchRequestVolley.matchRequestVolley.matchRequestVolley(
                baseContext,
                num
            ){
                if(it){
                    subscribe(
                        "/topic/match/request/",
                        matchRequestVolley.matchRequestVolley.num
                    )
                    println("plzdoit!" + matchrein)
                }
                else{
                    println("Fukkkkkkkkkk!")
                }
            }

        }
        match_com.setOnClickListener {
            matchsucVolley.matchsucVolley.matchsucVolley( // 요청자
                baseContext,
                matchrein
            ){
                if(it){
                    num3 = matchsucin
                    println("num12345$num3")
                    subscribe("topic/match/done/", num3)
                }
                   else{
                    println("Fukkkkkkkkkk2!")
                }
            } // 이부분 요청자 기준에서 수정


        }
        com.setOnClickListener {

            num4 = matchingPk
            runComVolley.runComVolley.runComVolley(baseContext, num4) // 수행자
        }
        com_com.setOnClickListener {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            num5 = matchsucin
            println("num12345$num5")
            runcomcomVolley.runcomcomVolley.runcomcomVolley(
                baseContext,
                num5
            ) // 요청자
        }

        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyvlerv.layoutManager = manager
        recyvlerv.adapter = ChatRoomActivity2.Companion.chatAdpater
        Handler().postDelayed(
            { recyvlerv.scrollToPosition(recyvlerv.adapter!!.itemCount - 1) },
            200
        )

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initData() {
        datalist.clear()
        datalist.add(Dataitem("채팅에 입장했습니다.", null, Code.ViewType.CENTER_CONTENT,"1"))
        chatlogVolley(chatindex, this)
    }

    companion object {

        var datalist = ArrayList<Dataitem>()
        var index = 0
        var chtmsg = String()
        var opponick = nicksend
        var chatAdpater = ChatAdpater(opponick, datalist)
        var msg1 = ""
        var ID1 = ""

        @RequiresApi(Build.VERSION_CODES.O)
        fun leftData(msg1: String?, nickname1: String?, date : String) {
            datalist.add(Dataitem(msg1, nickname1, Code.ViewType.LEFT_CONTENT,date))
            chatAdpater.notifyDataSetChanged()

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun rightData(msg: String?, dat2:String) {
            datalist.add(Dataitem(msg, null, Code.ViewType.RIGHT_CONTENT,dat2))
            chatAdpater.notifyDataSetChanged()

        }

        fun getMsg(msg: String, nickname: String): Boolean {
            msg1 = msg
            ID1 = nickname
            return true
        }
    }
}
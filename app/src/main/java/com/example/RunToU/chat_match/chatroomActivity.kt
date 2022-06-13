package com.example.RunToU.chat_match

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.RunToU.R
import com.example.RunToU.stomp_handler.stompClass.stompclass.send
import com.example.RunToU.stomp_handler.stompClass.stompclass.subscribe
import com.example.RunToU.chat_match.listtochatData.chatindex
import com.example.RunToU.chat_match.listtochatData.matchrein
import com.example.RunToU.chat_match.listtochatData.matchsucin
import com.example.RunToU.chat_match.listtochatData.nicksend
import com.example.RunToU.chat_match.listtochatData.requester
import com.example.RunToU.volley.chatlogVolley.chatlogVolley.chatlogVolley
import com.example.RunToU.chat_match.matchObjcet.matchingPk
import com.example.RunToU.volley.matchRequestVolley
import com.example.RunToU.volley.matchsucVolley
import com.example.RunToU.volley.runrreqVolley
import com.example.RunToU.volley.runsucVolley
import java.util.*


class chatroomActivity : Activity() {
    var num // 채팅방
            = 0
    var num2 // 매칭리ㅞ스트pk
            = 0
    var num3 // 매칭pk
            = 0
    var num4 = 0
    var num5 = 0
    override fun onCreate(savedinstanceState: Bundle?) {
        super.onCreate(savedinstanceState)
        setContentView(R.layout.room2)
        val chatmsg: TextView
        val but1: ImageButton
        val match: Button
        val com: Button
        val match_com: Button
        val com_com: Button
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
            chtmsg = chatmsg.text.toString()
            chatmsg.text = ""

            send(
                chtmsg,
                "/app/chat/chatroom/",
                num
            )
        }
        match.setOnClickListener { // 수행자
            matchRequestVolley.matchRequestVolley.matchRequestVolley(
                baseContext,
                num
            ) {
                if (it) {
                    subscribe(
                        "/topic/match/request/",
                        matchRequestVolley.matchRequestVolley.num
                    )

                } else {

                }
            }

        }
        match_com.setOnClickListener {
            matchsucVolley.matchsucVolley.matchsucVolley(
                baseContext,
                matchrein
            ) {
                if (it) {
                    num3 = matchsucin

                    subscribe("topic/match/done/", num3)
                } else {

                }
            } // 이부분 요청자 기준에서 수정


        }
        com.setOnClickListener {

            num4 = matchingPk
            runrreqVolley.runComVolley.runComVolley(baseContext, num4) // 수행자
        }
        com_com.setOnClickListener {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            num5 = matchsucin

            runsucVolley.runcomcomVolley.runcomcomVolley(
                baseContext,
                num5
            ) // 요청자
        }
        val recyvlerv: RecyclerView = findViewById(R.id.recyvlerv)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyvlerv.layoutManager = manager
        recyvlerv.adapter = chatAdpater
        Handler().postDelayed(
            { recyvlerv.scrollToPosition(recyvlerv.adapter!!.itemCount - 1) },
            200
        )
    }

    private fun initData() {
        datalist.clear()
        datalist.add(
            chatData(
                "채팅에 입장했습니다.",
                null,
                chatmsgstyleCode.ViewType.CENTER_CONTENT
            )
        )
        chatlogVolley(chatindex, this)
    }

    companion object {
        var datalist = ArrayList<chatData>()
        var index = 0
        var chtmsg = String()
        var opponick = nicksend
        var chatAdpater = ChatAdpater(opponick, datalist)
        var msg1 = ""
        var ID1 = ""
        fun leftData(msg1: String?, nickname1: String?) {
            datalist.add(
                chatData(
                    msg1,
                    nickname1,
                    chatmsgstyleCode.ViewType.LEFT_CONTENT
                )
            )
            chatAdpater.notifyDataSetChanged()
        }

        fun rightData(msg: String?) {
            datalist.add(
                chatData(
                    msg,
                    null,
                    chatmsgstyleCode.ViewType.RIGHT_CONTENT
                )
            )
            chatAdpater.notifyDataSetChanged()
        }

        fun getMsg(msg: String, nickname: String): Boolean {
            msg1 = msg
            ID1 = nickname
            return true
        }
    }
}
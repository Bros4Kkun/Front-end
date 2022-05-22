package com.example.RunToU

import android.content.Context
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

class chatroomVolley { //채팅룸 생성
    object chatroomVolley{

        val url = "http://3.39.87.103/api/chatroom/ordersheet/"
        fun chatroomVolley(
            context:Context,
            index :String,
        success:(Boolean) ->Unit
        ){
            var responseJson = JSONObject()




            val request = object : JsonObjectRequest(
                Request.Method.POST,
                url+index,
                null,
                Response.Listener<JSONObject> { response ->
                    print(response)
                    responseJson=response
                    chatRoomAdapter.orPK = responseJson.getInt("ordererPk")
                    chatRoomAdapter.prPK=responseJson.getInt("performerPk")
                    chatRoomAdapter.or_sh_pk = responseJson.getInt("orderSheetPk")
                    chatRoomAdapter.chRo = responseJson.getInt("chatRoomPk")
                    chatRoomAdapter.bool =responseJson.getBoolean("new")
                    success(true)


                },
                Response.ErrorListener { error ->
                    println("Error : $error")
                    success(false)
                }
            ) {
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String,String>()
                    headers.put("Content-Type", "application/json")
                    headers.put("COOKIE",SessionControl.SessionControl.sess.toString())
                    return headers
                }



            }
            val queue = Volley.newRequestQueue(context).add(request)
        }
    }
}
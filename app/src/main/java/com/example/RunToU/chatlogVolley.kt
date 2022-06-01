package com.example.RunToU

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

class chatlogVolley {
    object chatlogVolley{
        var responseJson = JSONObject()
        var jsonArray1= JSONArray()

        fun chatlogVolley(

            index : Int,
            context:Context,

        ){
            val url = "http://3.39.87.103/api/chatroom/"



            val request = @RequiresApi(Build.VERSION_CODES.O)
            object : JsonObjectRequest(
                Request.Method.GET,
                url + index.toString(),
                null,
                Response.Listener<JSONObject> { response ->
                    print(response)
                    responseJson =response
                        jsonArray1 = responseJson.getJSONArray("messageList")

                    for(i in 0..jsonArray1.length()-1) {
                        var IDCHECK = jsonArray1.getJSONObject(i).getString("writerAccountId")
                        var content = jsonArray1.getJSONObject(i).getString("content")
                        var NICK = jsonArray1.getJSONObject(i).getString("writerNickname")
                        var time = jsonArray1.getJSONObject(i).getString("createdDate")
                        if(IDCHECK!=chatRecieve.chatRecieve.loginID){
                        ChatRoomActivity2.leftData(content.replace("\n", ""),NICK,time)
                        } else {

                        ChatRoomActivity2.rightData(content.replace("\n", "",),time)

                        }
                    }





                },
                Response.ErrorListener { error ->
                    println("Error : $error")

                }
            ) {
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Content-Type", "application/json")
                    headers.put("COOKIE", SessionControl.SessionControl.sess.toString())
                    return headers
                }
            }



            val queue = Volley.newRequestQueue(context).add(request)
        }

    }}
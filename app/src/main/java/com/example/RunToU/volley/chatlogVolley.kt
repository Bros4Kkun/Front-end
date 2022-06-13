package com.example.RunToU.volley

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.RunToU.chat_match.chatroomActivity
import com.example.RunToU.login.authenticationData
import com.example.RunToU.chat_match.chatRecieve
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class chatlogVolley {
    object chatlogVolley{
        var responseJson = JSONObject()
        var jsonArray1= JSONArray()

        fun chatlogVolley(

            index : Int,
            context:Context,

        ){
            val url = "http://3.39.87.103/api/chatroom/"



            val request = object : JsonObjectRequest(
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
                        if(IDCHECK!= chatRecieve.chatRecieve.loginID){
                            chatroomActivity.leftData(content.replace("\n", ""), NICK)
                        } else {

                            chatroomActivity.rightData(content.replace("\n", ""))

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
                    headers.put("COOKIE", authenticationData.SessionControl.sess.toString())
                    return headers
                }
            }



            val queue = Volley.newRequestQueue(context).add(request)
        }

    }}
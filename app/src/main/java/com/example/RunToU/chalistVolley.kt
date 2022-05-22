package com.example.RunToU

import android.content.Context
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

class chatlistVolley {
    object chatlistVolley{
        var responseJson = JSONArray()
        val url = "http://3.39.87.103/api/chatroom"

        fun chatroomExsitVolley(
            context:Context,
            success:(Boolean) ->Unit
        ){

            val request = object : JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener<JSONArray> { response ->
                    print(response)
                    responseJson =response
                    for(i in 0..responseJson.length()-1) {
                        var jsonObject: JSONObject = responseJson.getJSONObject(i)
                        MsgFragment.datalist.add(chatlistdata(
                        jsonObject.getInt("chatRoomPk"),
                        jsonObject.getString("latestChatMessage"),
                        jsonObject.getJSONObject("ordererInfo").getString("nickname"),
                        jsonObject.getJSONObject("performerInfo").getString("nickname"),
                        jsonObject.getBoolean("matched")))

                    }
                    success(true)


                },
                Response.ErrorListener { error ->
                    println("Error : $error")
                    success(false)
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
package com.example.RunToU

import android.content.Context
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

object chatroomExistVolley {

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
                    responseJson=response

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
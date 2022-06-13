package com.example.RunToU.ordersheet

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.RunToU.chat_match.listtochatData
import com.example.RunToU.login.authenticationData
import org.json.JSONObject

class actingVolley {
    object actingVolley{
        val url =  "http://3.39.87.103/api/api/ordersheet/accompany-role-acting/cost"
        fun actingVolley(context: Context, int1 : Int,int2 : Int, duplicatedNickname: (Boolean) -> Unit)
        {

            val Json = JSONObject()
            Json.put("minutes",int1)
            Json.put("level",int2)
            var result = String()
            val requestBody= Json.toString()


            val request = object : JsonObjectRequest(
                Request.Method.GET,
                url ,
                Json,
                Response.Listener<JSONObject> { response ->
                    print(response)
                    listtochatData.cost =response.getInt("recommendCost")


                },
                Response.ErrorListener { error ->
                    kotlin.io.println("Error : $error")

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
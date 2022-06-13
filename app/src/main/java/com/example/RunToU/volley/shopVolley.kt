package com.example.RunToU.volley

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.RunToU.chat_match.listtochatData
import com.example.RunToU.login.authenticationData
import org.json.JSONObject

class shopVolley {
    object shopVolley{
        val url =  "http://3.39.87.103/api/ordersheet/delivery-shopping/cost"
        fun shopVolley(context: Context, int1 : Int,int2 : Int, int3:String,it: (Boolean) -> Unit)
        {

            val Json = JSONObject()
            Json.put("distance",-1)
            Json.put("minutes",int1)
            Json.put("cost",int3.toInt())
            var result = String()
            val requestBody= Json.toString()


            val request = object : JsonObjectRequest(
                Request.Method.POST,
                url ,
                Json,
                Response.Listener<JSONObject> { response ->
                    print(response)
                    listtochatData.cost =response.getInt("recommendCost")
                    it(true)

                },
                Response.ErrorListener { error ->
                    kotlin.io.println("Error : $error")
                    it(false)
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
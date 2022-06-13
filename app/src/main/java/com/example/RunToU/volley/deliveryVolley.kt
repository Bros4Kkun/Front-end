package com.example.RunToU.volley

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.RunToU.login.authenticationData
import com.example.RunToU.chat_match.listtochatData
import org.json.JSONObject

class deliveryVolley {
    object deliveryVolley{
        val url =  "http://3.39.87.103/api/ordersheet/delivery-installation/cost"
        fun deliveryVolley(context: Context, int1 : Int,int2 : Int, c: (Boolean) -> Unit)
        {

            var Json = JSONObject()
            Json.put("minutes",int1)
            Json.put("level",int2)
            var result = String()
            val requestBody= Json.toString()


            val request = object : JsonObjectRequest(
                Request.Method.POST,
                url ,
                Json,
                Response.Listener<JSONObject> { response ->
                    print(response)
                    listtochatData.cost =response.getInt("recommendCost")
                    c(true)

                },
                Response.ErrorListener { error ->
                    kotlin.io.println("Error : $error")
                    c(false)
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
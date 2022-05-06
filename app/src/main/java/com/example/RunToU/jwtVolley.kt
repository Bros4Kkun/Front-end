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

class jwtVolley {
    object jwtVolley {
        val url = "http://3.39.87.103/api/chat/jwt"
        fun jwtvolley(
            context : Context,
            success: (Boolean) -> Unit
        ) {

            val request = object : JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener<JSONObject> { response ->
                    print(response)
                    val tokenjson = response
                    val token = tokenjson.getString("token")
                    SessionControl.SessionControl.token= token
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
                    headers.put("Cookie",SessionControl.SessionControl.sess.toString())
                    return headers
                }


            }
            val queue = Volley.newRequestQueue(context).add(request)
        }
    }
}
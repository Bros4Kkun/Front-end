package com.example.RunToU.volley

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.RunToU.login.authenticationData
import org.json.JSONObject
import java.util.*

class jwtVolley {
    object jwtVolley {
        val url = "http://3.39.87.103/api/chat/jwt"
        fun jwtvolley(
            context : Context,

        ) {

            val request = object : JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener<JSONObject> { response ->
                    print(response)
                    val tokenjson = response
                    val token = tokenjson.getString("token")
                    authenticationData.SessionControl.token = token





                },
                Response.ErrorListener { error ->
                    println("Error : $error")

                }
            ) {
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Content-Type", "application/json")
                    headers.put("Cookie", authenticationData.SessionControl.sess.toString())
                    return headers
                }


            }
            val queue = Volley.newRequestQueue(context).add(request)
        }
    }
}
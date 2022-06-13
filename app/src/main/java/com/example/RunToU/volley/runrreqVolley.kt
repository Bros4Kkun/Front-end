package com.example.RunToU.volley

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.RunToU.login.authenticationData
import org.json.JSONObject
import java.util.*

class runrreqVolley {
    object runComVolley{
        var responseJson = JSONObject()
        val url = "http://3.39.87.103/api/match/job/done/"

        fun runComVolley(
            context: Context,
            int : Int,

            ){




            val request = object : JsonObjectRequest(
                Request.Method.POST,
                url + int.toString(),
                null,
                Response.Listener<JSONObject> { response ->
                    print(response)




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
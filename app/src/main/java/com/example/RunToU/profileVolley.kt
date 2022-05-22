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


    object profileVolley{
        var responseJson = JSONObject()
        val url = "http://3.39.87.103/api/user/profile/"
        var nick : String= ""
        var selfintro : String = ""
        fun profileVolley(
            context: Context,
            string:String,
            success:(Boolean) ->Unit
        ){




            val request = object : JsonObjectRequest(
                Request.Method.GET,
                url + string,
                null,
                Response.Listener<JSONObject> { response ->
                    print(response)
                    response.getString("nickname")
                    response.getString("selfIntroduction")


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

    }
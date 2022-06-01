package com.example.RunToU

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

object introducechangeVolley {
    var responseJson = JSONArray()
    val url = "http://3.39.87.103/api/user/profile/introduction"
    fun introducechangeVolley(
        context: Context,
        string : String
    ){
        var jObject = JSONObject()
        jObject.put("selfIntroduction",string)



        val request = object : JsonObjectRequest(
            Request.Method.POST,
            url,
            jObject,
            Response.Listener<JSONObject> { response ->
                print(response)
            },
            Response.ErrorListener { error ->
                println("Error : $error")

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
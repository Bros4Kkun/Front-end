package com.example.RunToU


import android.content.Context
import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.net.CookieManager
import java.net.CookieStore
import java.util.*
import kotlin.collections.HashMap


class loginvolley {

    object loginvolley{

        val url = "http://3.39.87.103/api/user/signin"
        fun loginvolley(
            context: Context,
            id: String,
            pw: String,
            role: String,
            success: (Boolean) -> Unit
        ){
            val logJson = JSONObject()
            logJson.put("accountId", id)
            logJson.put("rawPassword", pw)
            logJson.put("role", role)

            val requestBody = logJson

            val request = object : JsonObjectRequest(
                Request.Method.POST,
                url,
                logJson,
                Response.Listener<JSONObject> {response ->
                    print(response)
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
                    return headers
                }

                override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject> {
                    val cookiesinfo : java.util.TreeMap<String,String> = response?.headers as TreeMap<String, String>
                    val cookie = cookiesinfo.get("Set-Cookie")
                    println(cookie)
                    return super.parseNetworkResponse(response)
                    SessionControl.SessionControl.sess=cookie



                }

            }
            val queue = Volley.newRequestQueue(context).add(request)
        }
    }
}
package com.example.RunToU.volley


import android.content.Context
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.example.RunToU.login.authenticationData
import org.json.JSONObject
import java.util.*


class loginvolley {

    object loginvolley{
        var userindex : Int = 0
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



            val request = object : JsonObjectRequest(
                Request.Method.POST,
                url,
                logJson,
                Response.Listener<JSONObject> {response ->
                    print(response)

                    userindex = response.getInt("userPk")
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
                    authenticationData.SessionControl.sess =cookie
                    return super.parseNetworkResponse(response)



                }

            }
            val queue = Volley.newRequestQueue(context).add(request)
        }
    }
}
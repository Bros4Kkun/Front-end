package com.example.runtyou


import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Request.Method.POST
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.reflect.Method

class loginvolley {
    object loginvolley{
        val url = "http://3.38.254.41/api/user/signin"
        fun loginvolley(context: Context, id : String, pw : String, role : String, success: (Boolean) -> Unit){
            val logJson = JSONObject()
            logJson.put("accountId",id)
            logJson.put("rawPassword",pw)
            logJson.put("role",role)

            val requestBody = logJson.toString()

            val LoginRequest = object : StringRequest(Method.POST,
                url, Response.Listener { response ->
                println("서버수신 : $response")
                success(true)
                println(requestBody)
            }, Response.ErrorListener { error ->
                println("Error : $error")
                success(false)
            }){
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                override fun getBody(): ByteArray{
                    return requestBody.toByteArray()
                }
            }
            Volley.newRequestQueue(context).add(LoginRequest)



        }
    }
}
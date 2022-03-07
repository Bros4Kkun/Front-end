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

class nickvolley {
    object nickvolley{
        val url =  "http://3.38.254.41/api/user/nickname"
        fun nickcheckVolley(context: Context, nick : String, duplicatedNickname: (Boolean) -> Unit)
        {

            val idJson = JSONObject()
            idJson.put("accountid",nick)

            val requestBody= idJson.toString()


            val idReuqest = object : StringRequest(
                Method.POST,
                url, Response.Listener { response ->
                    println("서버수신 : $response")
                    duplicatedNickname(true)
                    println(requestBody)
                }, Response.ErrorListener { error ->
                    println("Error : $error")
                    duplicatedNickname(false)
                }){
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                override fun getBody(): ByteArray{
                    return requestBody.toByteArray()
                }
            }
            Volley.newRequestQueue(context).add(idReuqest)


        }
    }

}
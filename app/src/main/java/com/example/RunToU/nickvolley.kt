package com.example.RunToU

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.sql.DriverManager.println

class nickvolley {
    object nickvolley{
        val url =  "http://3.39.87.103/api/user/signup/nickname"
        fun nickcheckVolley(context: Context, nick : String, duplicatedNickname: (Boolean) -> Unit)
        {

            val idJson = JSONObject()
            idJson.put("nickname",nick)
            var result = String()
            val requestBody= idJson.toString()


            val idReuqest = object : StringRequest(
                Method.POST,
                url, Response.Listener { response ->
                    println("서버수신 : $response")
                    result = response.substring(22)
                    println(result)
                    if(result == "true}"){
                        duplicatedNickname(true)
                    }
                    else{
                        duplicatedNickname(false)
                    }
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
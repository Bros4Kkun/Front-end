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

class idvolley {
    object idvolley{
    val url =  "http://3.38.254.41/api/user/signup/accountid"
    fun idcheckVolley(context: Context, id : String, duplicatedAccountId: (Boolean) -> Unit)
    {

        val idJson = JSONObject()
        idJson.put("accountId",id)
        var result = String()
        val responseJson = JSONObject()

        val requestBody= idJson.toString()


        val idReuqest = object : StringRequest(
            Method.POST,
            url, Response.Listener { response ->
            println("서버수신 : $response")
                result = response.substring(23)
                println(result)
                if(result == "true}"){
                    duplicatedAccountId(true)
                }
                else{
                    duplicatedAccountId(false)
                }
            println(requestBody)
        }, Response.ErrorListener { error ->
            println("Error : $error")
            duplicatedAccountId(false)
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
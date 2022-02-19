package com.example.runtyou

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class volleyService {
    object VolleyService {
        val url = "http://3.38.254.41/api/user/signup"

        fun testVolley(context: Context,account: String, nickname : String,password : String,phonenumber : String, accountnum : String, success: (Boolean) -> Unit ) {
            val myJson = JSONObject()

            myJson.put("accountId",account)
            myJson.put("nickname",nickname)
            myJson.put("password",password)
            myJson.put("phoneNumber",phonenumber)
            myJson.put("accountNumber",accountnum)

            val requestBody = myJson.toString()

            val SignupReuqest = object : StringRequest(Method.POST, url, Response.Listener { response ->
                println("서버수신 : $response")
                success(true)
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
            Volley.newRequestQueue(context).add(SignupReuqest)
        }
    }
}
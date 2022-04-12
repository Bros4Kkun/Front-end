package com.example.RunToU

import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit

import java.net.CookieManager


class RetrofitService {
    val baseUrl = "http://12.345.678.910"

    val okHttpClient = OkHttpClient.Builder()
        .cookieJar(JavaNetCookieJar(CookieManager()))
        .build()


    //Retrofit 객체 초기화
    val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(this.baseUrl)
        .build()
    val test : TestService = retrofit.create(TestService::class.java)


}
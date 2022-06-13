package com.example.RunToU.stomp_handler

import android.annotation.SuppressLint
import android.os.Handler
import com.example.RunToU.chat_match.chatRecieve
import com.example.RunToU.login.authenticationData

import com.example.stompclient2.Event




import io.reactivex.disposables.Disposable
import okhttp3.*

import java.util.*
import java.util.concurrent.TimeUnit

import java.util.logging.Logger

import com.example.stompclient2.StompClient

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.lang.Exception


class stompClass: WebSocketListener() {
    object stompclass : WebSocketListener() {
        lateinit var stompConnection: Disposable

        var resultString: String? =""

        val handler : Handler = MyHandler()
        val logger = Logger.getLogger("STOMP")
        val url1 = "ws://3.39.87.103/ws-stomp"
        val intervalMillis = 100L
        val client = OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .header("Authentication", "Bearer " + authenticationData.SessionControl.token)
                        .build()
                )
            }
            .addInterceptor {
                it.proceed(
                    it.request().newBuilder().header("accept-version", "1.0,1.1,1.2").build()
                )
            }
            .addInterceptor {
                it.proceed(
                    it.request().newBuilder().header("heart-beat", "6000,0").build()
                )
            }
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

        val stomp = StompClient(client, intervalMillis, handler).apply { this@apply.url = url1 }

        fun connect(api : String,int: Int) {
            stompConnection = stomp.connect().subscribe() {   //연결
                when (it.type) {
                    Event.Type.OPENED -> {
                        print("connected succcccc!")
                        if(api != "none") {
                            stomp.join(api + int.toString(),) // 응답받기 위한 구독
                                .subscribe {
                                    chatRecieve.chatRecieve.recivemsg = it.toString()
                                }

                        }
                    }
                    Event.Type.CLOSED -> { // log보고 반복메시지 보내는 이유 분석

                    }
                    Event.Type.ERROR -> {

                    }

                }


            }

        }
        @SuppressLint("CheckResult")
        fun send(msg: String?,api: String, int: Int){
            if(!msg.isNullOrEmpty()){
                try {
                    stomp.send(
                        api + int.toString(),
                        msg.toString(),
                        "Bearer " + authenticationData.SessionControl.token
                    ).subscribe() { echo -> // send

                        if (echo) {
                            println("test!!!!!!!3!!!" + msg + int.toString())


                        }
                    }
                }catch(e:Exception){
                    println("fuckingError!"+e.toString())
                }
            }
        }

        fun subscribe(topic : String,int: Int) {
            CoroutineScope(Dispatchers.IO).async {
                val def = async {
                    val join = stomp.join(topic + int.toString(),) // 응답받기 위한 구독
                        .subscribe {
                            chatRecieve.chatRecieve.recivemsg = it.toString()
                        }

                }


            }

        }







        }


    }

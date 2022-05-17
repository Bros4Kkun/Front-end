package com.example.RunToU

import android.annotation.SuppressLint
import android.os.Handler

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
import okhttp3.internal.http2.Http2Reader



class Stompclass: WebSocketListener() {
    object Stomclass : WebSocketListener() {
        lateinit var stompConnection: Disposable

        var resultString: String? =""

        val handler : Handler = MyHandler()
        val logger = Logger.getLogger("STOMP")
        val url1 = "ws://3.39.87.103/ws-stomp"
        val intervalMillis = 1000L
        val client = OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .header("Authentication", "Bearer " + SessionControl.SessionControl.token)
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
            .readTimeout(10000, TimeUnit.MINUTES)
            .writeTimeout(10000, TimeUnit.MINUTES)
            .connectTimeout(1000, TimeUnit.SECONDS)
            .build()

        val stomp = StompClient(client, intervalMillis,handler).apply { this@apply.url = url1 }

        fun connect() {
            stompConnection = stomp.connect().subscribe() {   //연결
                when (it.type) {
                    Event.Type.OPENED -> {
                        print("connected succcccc!")



                    }
                    Event.Type.CLOSED -> { // log보고 반복메시지 보내는 이유 분석

                    }
                    Event.Type.ERROR -> {

                    }

                }


            }

        }
        @SuppressLint("CheckResult")
        fun send(msg: String?, int: Int){
            if(!msg.isNullOrEmpty()){
                stomp.send(
                    "/app/chat/chatroom/"+int.toString(),
                    msg.toString(),
                    "Bearer " + SessionControl.SessionControl.token
                ).subscribe() { echo -> // send

                    if (echo) {
                        println("test!!!!!!!3!!!" + msg + int.toString())


                    }
                }
            }
        }





            fun subscribe(int: Int) {
                CoroutineScope(Dispatchers.IO).async {
                    val def = async {
                          val join = stomp.join("/topic/chatroom/" + int.toString(),) // 응답받기 위한 구독
                            .subscribe {
                                chatRecieve.chatRecieve.recivemsg = it.toString()
                            }

                    }


                }

            }



        }


    }

package com.example.RunToU

import android.annotation.SuppressLint
import com.example.RunToU.SessionControl.SessionControl.token
import com.example.stompclient2.Event
import com.example.stompclient2.Message


import com.example.stompclient2.constants.Commands
import com.example.stompclient2.constants.Headers
import io.reactivex.disposables.Disposable
import okhttp3.*
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.nio.channels.AsynchronousChannel
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.collections.HashMap
import com.example.stompclient2.StompClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class Stompclass: WebSocketListener() {
    object Stomclass : WebSocketListener() {
        lateinit var stompConnection: Disposable
        var resultString: String = ""
        var recivecheck: Boolean = false
        private lateinit var webSocket: WebSocket

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

        val stomp = StompClient(client, intervalMillis).apply { this@apply.url = url1 }

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





            fun subscribe(int: Int): String {
                CoroutineScope(Dispatchers.Default).async {
                    val def = async {
                        stomp.join("/topic/chatroom/" + int.toString()) // 응답받기 위한 구독
                            .subscribe {

                                logger.log(Level.INFO, it)



                                print(it)


                            }
                    }
                    resultString = def.await().toString()
                    print(resultString)
                }
                return resultString
            }


    }
}
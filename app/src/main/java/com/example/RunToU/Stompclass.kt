package com.example.RunToU

import com.example.RunToU.SessionControl.SessionControl.token
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import java.nio.channels.AsynchronousChannel
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger

class Stompclass {
    object Stomclass {
        lateinit var stompConnection: Disposable
        lateinit var topic: Disposable

        val logger = Logger.getLogger("STOMP")
        val url1 = "ws://3.39.87.103/ws-stomp"
        val intervalMillis = 1000L
        val client = OkHttpClient.Builder()
            .addInterceptor { it.proceed(it.request().newBuilder().header("Authentication", token.toString()).build()) }
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(1000, TimeUnit.SECONDS)
            .build()
        val stomp = StompClient(client, intervalMillis).apply { this@apply.url = url1 }

        fun connect(msg : String) {
            stompConnection = stomp.connect().subscribe() {   //연결
                when (it.type) {
                    Event.Type.OPENED -> {
                        print("connected succcccc!")

                    }
                    Event.Type.CLOSED -> {

                    }
                    Event.Type.ERROR -> {

                    }
                }
                topic = stomp.join("/topic/chatroom/3") // 응답받기 위한 구독
                    .subscribe { logger.log(Level.INFO, it)

                        chatRecieve.chatRecieve.recieveMsg = it
                        print(it)

                    }
                    stomp.send("/app/chat/chatroom/3", msg).subscribe(){echo -> // send

                    if(echo){
                        println("test!!!!!!!!!!"+echo.toString())

                    }

                }

            }



        }
        fun subscribe(){

        }

        fun send() {


        }
    }
}
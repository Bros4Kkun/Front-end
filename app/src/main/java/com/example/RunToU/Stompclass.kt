package com.example.RunToU

import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
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
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(1000, TimeUnit.SECONDS)
            .build()
        val stomp = StompClient(client, intervalMillis).apply { this@apply.url = url1 }

        fun connect(msg : String) {
            stompConnection = stomp.connect().subscribe() {
                when (it.type) {
                    Event.Type.OPENED -> {


                    }
                    Event.Type.CLOSED -> {

                    }
                    Event.Type.ERROR -> {

                    }
                }
                topic = stomp.join("/topic/greetings")
                    .subscribe { logger.log(Level.INFO, it)

                        chatRecieve.chatRecieve.recieveMsg = it

                    }
                stomp.send("/app/hello-msg-mapping", msg).subscribe(){echo ->

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
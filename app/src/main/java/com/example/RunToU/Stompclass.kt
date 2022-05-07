package com.example.RunToU

import com.example.RunToU.SessionControl.SessionControl.token
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.Message
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.gmail.bishoybasily.stomp.lib.constants.Commands
import com.gmail.bishoybasily.stomp.lib.constants.Headers
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

class Stompclass {
    object Stomclass {
        lateinit var stompConnection: Disposable
        lateinit var topic: Disposable
        private lateinit var webSocket: WebSocket


        val logger = Logger.getLogger("STOMP")
        val url1 = "ws://3.39.87.103/ws-stomp"
        val intervalMillis = 1000L
        val client = OkHttpClient.Builder()
            .addInterceptor { it.proceed(it.request().newBuilder().header("Authentication","Bearer "+SessionControl.SessionControl.token).build()) }
            .addInterceptor { it.proceed(it.request().newBuilder().header("accept-version","1.0,1.1,1.2").build()) }
            .addInterceptor { it.proceed(it.request().newBuilder().header("heart-beat","6000,0").build()) }
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
                topic = stomp.join("/topic/chatroom/69") // 응답받기 위한 구독
                    .subscribe { logger.log(Level.INFO, it)

                        chatRecieve.chatRecieve.recieveMsg = it
                        print(it)

                    }
                    send("/app/chat/chatroom/69", msg).subscribe(){ echo -> // send

                    if(echo){
                        println("test!!!!!!!!!!"+echo.toString())

                    }

                }

            }



        }
        fun subscribe(){

        }

        fun send(topic: String, msg: String): Observable<Boolean> {
            return Observable
                .create<Boolean> {
                    val headers = HashMap<String, String>()
                    headers.put(Headers.DESTINATION , topic)
                    headers.put("Authentication","Bearer "+SessionControl.SessionControl.token)
                    it.onNext(webSocket.send(compileMessage(Message(Commands.SEND, headers, msg))))
                    it.onComplete()
                }
        }
        private fun compileMessage(message: Message): String {
            val builder = StringBuilder()

            if (message.command != null)
                builder.append(message.command).append('\n')

            for ((key, value) in message.headers)
                builder.append(key).append(':').append(value).append('\n')
            builder.append('\n')

            if (message.payload != null)
                builder.append(message.payload).append("\n\n")

            builder.append(Message.TERMINATE_MESSAGE_SYMBOL)

            return builder.toString()
        }
    }
}
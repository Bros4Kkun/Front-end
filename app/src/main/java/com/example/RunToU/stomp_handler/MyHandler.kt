package com.example.RunToU.stomp_handler

import android.os.Handler
import android.os.Message
import com.example.RunToU.main.MainActivity

class MyHandler : Handler() {
val handler : Handler = MainActivity().leftHanlder()
    companion object {
        const val TAG = "MyHandler"
        const val MSG_DO_SOMETHING1 = 1
        const val MSG_DO_SOMETHING2 = 2
        const val MSG_DO_SOMETHING3 = 3
        const val MSG_DO_SOMETHING4 = 4
        const val MSG_DO_SOMETHING5 = 5
    }

    override fun handleMessage(msg: Message) {

        when (msg.what) {
            MSG_DO_SOMETHING1 -> {
                val msg1 = handler.obtainMessage(2)

                handler.handleMessage(msg1)
            }
            MSG_DO_SOMETHING2 -> {
                val msg1 = handler.obtainMessage(1)

                handler.handleMessage(msg1)

            }
            MSG_DO_SOMETHING3 -> {
                val msg1 = handler.obtainMessage(3)

                handler.handleMessage(msg1)
            }
            MSG_DO_SOMETHING4 -> {
                val msg1 = handler.obtainMessage(4)

                handler.handleMessage(msg1)

            }
            MSG_DO_SOMETHING5 -> {
                val msg1 = handler.obtainMessage(5)

                handler.handleMessage(msg1)

            }
        }
    }


}
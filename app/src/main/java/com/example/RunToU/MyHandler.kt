package com.example.RunToU

import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.annotation.UiThread
import org.json.JSONObject

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
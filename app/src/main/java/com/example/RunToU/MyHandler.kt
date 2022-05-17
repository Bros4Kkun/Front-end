package com.example.RunToU

import android.os.Handler
import android.os.Message
import android.util.Log
import org.json.JSONObject

class MyHandler : Handler() {
    companion object {
        const val TAG = "MyHandler"
        const val MSG_DO_SOMETHING1 = 1
        const val MSG_DO_SOMETHING2 = 2
        const val MSG_DO_SOMETHING3 = 3
        const val MSG_DO_SOMETHING4 = 4
    }
    override fun handleMessage(msg: Message) {
        when (msg.what) {
            MSG_DO_SOMETHING1 -> {
                Log.d(TAG, "Do something1")
            }
            MSG_DO_SOMETHING2 -> {
                var inputmsg : String = chatRecieve.chatRecieve.recivemsg
                var jObject: JSONObject = JSONObject(inputmsg)

                if(jObject.getString("writerAccountId") != chatRecieve.chatRecieve.loginID){
                ChatRoomActivity2.leftData(jObject.getString("content").replace("\n",""),jObject.getString("writerAccountId"))
                    ChatRoomActivity2.index=1


                }
            }
            MSG_DO_SOMETHING3 -> {
                Log.d(TAG, "Do something3")
            }
            MSG_DO_SOMETHING4 -> {
                Log.d(TAG, "Do something4, arg1: ${msg.arg1}," +
                        " arg2: ${msg.arg2}, obj: ${msg.obj}")
            }
        }
    }
}
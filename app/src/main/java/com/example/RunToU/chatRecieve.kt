package com.example.RunToU

import org.json.JSONObject
import java.util.*

class chatRecieve{
    object chatRecieve{
        var loginID : String = ""
        var msgquee : Queue<String> = LinkedList()
        var recivemsg : String = ""

        //임시 채팅방 번호
        var chatindex : Int = 0
    }
}

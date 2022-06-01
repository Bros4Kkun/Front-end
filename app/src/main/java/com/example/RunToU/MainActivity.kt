package com.example.RunToU


import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.*
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.RunToU.chatRecieve.chatRecieve.recivemsg
import com.example.RunToU.chatRoomAdapter.requester
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private fun createNotificationChannel() {
         val channelId = "RunToU"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "RunToU"
            val descriptionText = "RunToU"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    lateinit var  builder: NotificationCompat.Builder
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        chatroomExistVolley.chatroomExsitVolley(this){it->
            if(it){
                var jsonarray : JSONArray
                var jobject : JSONObject
                jsonarray = chatroomExistVolley.responseJson
                for(i in 0..jsonarray.length()-1) {
                    jobject= jsonarray.getJSONObject(i)
                    var index = jobject.getInt("chatRoomPk")
                    Stompclass.Stomclass.subscribe("/topic/chatroom/",index)
                    println(index.toString()+"was subscribed!")}


            }
            else{
                print("multi subscribe error!")}
        }
        matchExistVolley.matchExsitVolley(this){
            if(it){
                var jsonArray:JSONArray
                var jObject : JSONObject
                jsonArray = chatroomExistVolley.responseJson
                for(i in 0..jsonArray.length()-1){
                    jObject= jsonArray.getJSONObject(i)
                    if(jObject.getBoolean("matched") == false) {
                        println("completion?")
                        if(requester) {
                            var index = jObject.getJSONObject("performerInfo").getInt("id")
                            Stompclass.Stomclass.subscribe("/topic/match/done/", index)

                        }
                    }
                }
            }
        }


        configureBottomNavigation()


    }
    inner class leftHanlder : Handler() {

            val TAG = "MyHandler"
            val MSG_DO_SOMETHING1 = 1
        val MSG_DO_SOMETHING2 = 2
            val MSG_DO_SOMETHING3 = 3
            val MSG_DO_SOMETHING4 = 4
        val MSG_DO_SOMETHING5=5

        @RequiresApi(Build.VERSION_CODES.O)
        override fun handleMessage(msg: Message) {
            when (msg.what) {
            MSG_DO_SOMETHING1 -> {

                var inputmsg: String = chatRecieve.chatRecieve.recivemsg
                var jObject: JSONObject = JSONObject(inputmsg)

                if (jObject.getString("writerAccountId") != chatRecieve.chatRecieve.loginID) {
                    runOnUiThread(Runnable {
                        ChatRoomActivity2.leftData(
                            jObject.getString("content").replace("\n", ""),
                            jObject.getString("writerAccountId"),
                            jObject.getString("createdDate")
                        )


                    }
                    )

                }
                else{
                    runOnUiThread(Runnable {
                        ChatRoomActivity2.rightData(
                            jObject.getString("content").replace("\n","") ,
                                    jObject.getString("createdDate")
                        )

                    })

                }




                println("don'twork??")


            }
                MSG_DO_SOMETHING2 -> {
                    var inputmsg: String = chatRecieve.chatRecieve.recivemsg
                    var jObject: JSONObject = JSONObject(inputmsg)

                        matchObjcet.chatRoomPk = jObject.getInt("chatRoomPk") // queue/orderer
                        Stompclass.Stomclass.subscribe("/topic/match/chatroom/",matchObjcet.chatRoomPk)


                    println("don'twork??2")
                }
                MSG_DO_SOMETHING3 ->{
                    var inputmsg:String=chatRecieve.chatRecieve.recivemsg
                    var jObjects: JSONObject = JSONObject(inputmsg)
                    chatRoomAdapter.matchrein = jObjects.getInt("matchRequestId")
                    println("don'twork??3")
                }
                MSG_DO_SOMETHING4 -> {
                    var inputmsg: String = chatRecieve.chatRecieve.recivemsg
                    var jObject : JSONObject =JSONObject(inputmsg)
                    matchObjcet.matchingPk = jObject.getInt("matchingId")
                    println("zzzzzzzzzz"+matchObjcet.matchingPk)
                    println("dontwork??4")
                }
                MSG_DO_SOMETHING5 -> {
                    var inputmsg: String = chatRecieve.chatRecieve.recivemsg
                    var jObject : JSONObject =JSONObject(inputmsg)
                    chatRoomAdapter.matchsucin = jObject.getInt("matchingId")
                    println("don'twork??5")
                }
            }
        }
    }

    private fun configureBottomNavigation(){

        vp_ac_main_frag_pager.adapter = MainFragmentStatePagerAdapter(supportFragmentManager, 4)

        tl_ac_main_bottom_menu.setupWithViewPager(vp_ac_main_frag_pager)

        val bottomNaviLayout: View = this.layoutInflater.inflate(
            R.layout.bottom_navigation_tab,
            null,
            false
        )

        tl_ac_main_bottom_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_home_tab) as RelativeLayout
        tl_ac_main_bottom_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_search_tab) as RelativeLayout
        tl_ac_main_bottom_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_add_tab) as RelativeLayout
        tl_ac_main_bottom_menu.getTabAt(3)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_like_tab) as RelativeLayout
    }



}


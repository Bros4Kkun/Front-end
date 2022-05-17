package com.example.RunToU


import android.os.Bundle
import android.os.HandlerThread
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.RunToU.chatRecieve.chatRecieve.recivemsg
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {


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
                    Stompclass.Stomclass.subscribe(index)
                    println(index.toString()+"was subscribed!")}


            }
            else{
                print("multi subscribe error!")}
        }

            configureBottomNavigation()


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


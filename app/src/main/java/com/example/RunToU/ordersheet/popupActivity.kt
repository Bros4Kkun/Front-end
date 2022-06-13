package com.example.RunToU.ordersheet

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.RunToU.R
import com.example.RunToU.chat_match.listtochatData
import com.example.RunToU.volley.shopVolley
import com.example.RunToU.volley.accompanyVolley
import com.example.RunToU.volley.cleaningVolley
import com.example.RunToU.volley.deliveryVolley

class popupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = getIntent()

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)
        val txtPrice : TextView = findViewById(R.id.txtPrice)
        val text1 : EditText = findViewById(R.id.minute)
        val text2 : EditText = findViewById(R.id.level)
        var level : Int =0
        var minute : Int =0

        val button1 : Button = findViewById(R.id.cancel)
        val button2 : Button = findViewById(R.id.go)
        button1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                finish()
            }
        })
        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                minute = text1.text.toString().toInt()
                level = text2.text.toString().toInt()

                var cate : String = intent.getStringExtra("cate").toString()
                if (cate=="accompany-role-acting") {
                    accompanyVolley.accompanyVolley.accompanyVolley(
                        applicationContext,
                        minute,
                        level
                    ) {
                        if (it) {
                            Log.d("Tag" , "cost : " + listtochatData.cost.toString() )

                        }
                    }
                }
                    else if(cate=="cleaning-housework"){

                        cleaningVolley.cleaningVolley.cleaningVolley(applicationContext, minute, level) {
                            println("??????????????")
                            if (it) {
                                Toast.makeText(
                                    applicationContext,
                                    "추천하는 가격은 " + listtochatData.cost.toString() + "원 입니다.",
                                    Toast.LENGTH_LONG).show()
                                println("!!!!!!!!!!!!!!!!!!!!!")
                            }
                        }
                    }else if(cate=="delivery-installation"){
                        deliveryVolley.deliveryVolley.deliveryVolley(applicationContext, minute, level) {
                            if (it) {
                                Toast.makeText(
                                    applicationContext,
                                    "추천하는 가격은 " + listtochatData.cost.toString() + "원 입니다.",
                                    Toast.LENGTH_LONG).show()
                            }
                        }
                    }else{
                    var cost : String =intent.getStringExtra("cost").toString()
                    Log.d("Tag", "Cost : " + cost)
                    shopVolley.shopVolley.shopVolley(applicationContext, minute, level,cost) {
                            if (it) {
                                Toast.makeText(
                                    applicationContext,
                                    "추천하는 가격은 " + listtochatData.cost.toString() + "원 입니다.",
                                    Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }

        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (event.getAction() === MotionEvent.ACTION_OUTSIDE) {
            false
        } else true
    }

}
package com.example.runtyou

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var user : Int = 1
    private var userid : String? = null
    private var userpw : String? = null
    private var but1 : Button? =null
    private var but2 : Button? =null
    private var but3 : Button? =null
    override fun onCreate(savedInstanceState : Bundle?) {
        setTheme(R.style.Theme_Runtyou)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        but1 = per_login as Button
        but1!!.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){


                userid = log_id.text.toString()
                userpw = log_pw.text.toString()
                if (userid.equals("test") && userpw.equals("test") ) {
                    user = 1
                    Toast.makeText(this@LoginActivity, "수행자 로그인 성공", Toast.LENGTH_LONG).show()
                    val intent1 = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent1)

                } else {
                    Toast.makeText(this@LoginActivity, "아이디 혹은 비밀번호가 잘못됐습니다.", Toast.LENGTH_LONG).show()
                }
            }
        })

        but2 = ord_login as Button
        but2!!.setOnClickListener { }


    }
}
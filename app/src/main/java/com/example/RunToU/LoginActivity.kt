package com.example.RunToU

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.net.CookieHandler
import java.net.CookieManager


class LoginActivity : AppCompatActivity() {

    private var user : Int = 1
    private var userid : String? = null
    private var userpw : String? = null
    private val SET_COOKIE_KEY = "Set-Cookie"
    private val COOKIE_KEY = "Cookie"
    private val SESSION_COOKIE = "JSESSIONID"
    var login_hashMap = HashMap<String,Any>()


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Runtyou)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       val but1 = per_login as Button
        but1!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                userid = log_id.text.toString()
                userpw = log_pw.text.toString()

                loginvolley.loginvolley.loginvolley(
                    this@LoginActivity,
                    userid.toString(),
                    userpw.toString(),
                    "ORDERER"
                ) { success ->
                    if (success) {
                        user = 1
                        Toast.makeText(this@LoginActivity, "요청자 로그인 성공", Toast.LENGTH_SHORT).show()
                        val intent1 = Intent(this@LoginActivity, MainActivity::class.java)



                        startActivity(intent1)

                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "아이디 또는 비밀번호가 잘못됐습니다.",
                            Toast.LENGTH_SHORT

                        ).show()

                    }

                }
            }
        })

        val but2 = ord_login as Button
        but2!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                userid = log_id.text.toString()
                userpw = log_pw.text.toString()

                loginvolley.loginvolley.loginvolley(
                    this@LoginActivity,
                    userid.toString(),
                    userpw.toString(),
                    "ORDERER"
                ) { success ->
                    if (success) {
                        user = 1
                        Toast.makeText(this@LoginActivity, "요청자 로그인 성공", Toast.LENGTH_SHORT).show()
                        val intent1 = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent1)

                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "아이디 또는 비밀번호가 잘못됐습니다.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
        })

        val but3 = signup_button as Button
        but3!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent2 = Intent(this@LoginActivity, SingupActivity::class.java)
                startActivity(intent2)
            }
        })

        }

    }





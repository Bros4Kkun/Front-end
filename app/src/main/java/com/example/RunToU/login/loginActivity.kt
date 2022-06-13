package com.example.RunToU.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.RunToU.main.MainActivity
import com.example.RunToU.R
import com.example.RunToU.signup.signupActivity
import com.example.RunToU.stomp_handler.stompClass
import com.example.RunToU.chat_match.chatRecieve
import com.example.RunToU.chat_match.listtochatData
import com.example.RunToU.volley.profileVolley
import com.example.RunToU.volley.jwtVolley
import com.example.RunToU.volley.loginvolley
import com.example.RunToU.volley.matchcheckVolley
import kotlinx.android.synthetic.main.activity_login.*


class loginActivity : AppCompatActivity() {

    private var user : Int = 1
    private var userid : String? = null
    private var userpw : String? = null
    private val SET_COOKIE_KEY = "Set-Cookie"
    private val COOKIE_KEY = "Cookie"
    private val SESSION_COOKIE = "JSESSIONID"
    var login_hashMap = HashMap<String, Any>()


    override fun onCreate(savedInstanceState: Bundle?) {
        val ac: ActionBar? = supportActionBar
        ac?.setTitle("로그인")
        setTheme(R.style.Theme_Runtyou)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       val but1 = per_login as Button
        but1!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                userid = log_id.text.toString()
                userpw = log_pw.text.toString()

                loginvolley.loginvolley.loginvolley(
                    this@loginActivity,
                    userid.toString(),
                    userpw.toString(),
                    "PERFORMER"
                ) { success ->
                    if (success) {
                        user = 1
                        chatRecieve.chatRecieve.loginID = userid.toString()
                        Toast.makeText(this@loginActivity, "수행자 로그인 성공", Toast.LENGTH_SHORT).show()
                        jwtVolley.jwtVolley.jwtvolley(context = this@loginActivity)
                        matchcheckVolley.matchCheckVolley.matchsucVolley(this@loginActivity)
                        stompClass.stompclass.connect("none", 3)
                        profileVolley.profileVolley(this@loginActivity, chatRecieve.chatRecieve.loginID){
                            if(it){

                                println("success!")

                            }
                            else{}
                        }

                        val intent1 = Intent(this@loginActivity, MainActivity::class.java)



                        startActivity(intent1)

                        finish()
                    } else {
                        Toast.makeText(
                            this@loginActivity,
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
                    this@loginActivity,
                    userid.toString(),
                    userpw.toString(),
                    "ORDERER"
                ) { success ->
                    if (success) {
                        user = 1
                        chatRecieve.chatRecieve.loginID = userid.toString()
                        Toast.makeText(this@loginActivity, "요청자 로그인 성공", Toast.LENGTH_SHORT).show()
                        jwtVolley.jwtVolley.jwtvolley(context = this@loginActivity)
                        stompClass.stompclass.connect(
                            "/queue/orderer/",
                            loginvolley.loginvolley.userindex
                        ) // 로그인시 구독
                        val intent1 = Intent(this@loginActivity, MainActivity::class.java)
                        matchcheckVolley.matchCheckVolley.matchsucVolley(this@loginActivity)
                        listtochatData.requester = true
                        profileVolley.profileVolley(this@loginActivity, chatRecieve.chatRecieve.loginID){
                            if(it){

                                println("success!")

                            }
                            else{}
                        }
                        startActivity(intent1)

                        finish()
                    } else {
                        Toast.makeText(
                            this@loginActivity,
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
                val intent2 = Intent(this@loginActivity, signupActivity::class.java)
                startActivity(intent2)
            }
        })

        }

    }





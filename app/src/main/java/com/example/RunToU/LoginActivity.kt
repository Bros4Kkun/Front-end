package com.example.RunToU

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gmail.bishoybasily.stomp.lib.*
import io.reactivex.disposables.Disposable

import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger


class LoginActivity : AppCompatActivity() {

    private var user : Int = 1
    private var userid : String? = null
    private var userpw : String? = null
    val logger = Logger.getLogger("STOMP")

    lateinit var stompConnection : Disposable
    lateinit var topic : Disposable

    val url1 = "ws://3.39.87.103/ws-stomp"
    val intervalMillis = 1000L
    val client = OkHttpClient.Builder()
        .readTimeout(10,TimeUnit.SECONDS)
        .writeTimeout(10,TimeUnit.SECONDS)
        .connectTimeout(10,TimeUnit.SECONDS)
        .build()
    val stomp = StompClient(client, intervalMillis).apply { this@apply.url = url1 }





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
                        stompConnection = stomp.connect().subscribe(){
                            when(it.type){
                                Event.Type.OPENED -> {
                                    topic = stomp.join("/queue/orderer")
                                        .subscribe { logger.log(Level.INFO, it) }

                                }
                                Event.Type.CLOSED -> {

                                }
                                Event.Type.ERROR -> {

                                }
                            }
                        }
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





package com.example.runtyou

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.runtyou.volleyService.VolleyService.signVolley
import com.example.runtyou.idvolley.idvolley.idcheckVolley

import com.google.android.gms.common.api.Response
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_singup.*
import java.util.concurrent.TimeUnit



class SingupActivity : AppCompatActivity() {

        val auth = Firebase.auth
        var verificationId = ""
        private var sid: String = ""
        private var pw: String = ""
        private var nick: String = ""
        private var pwch: String = ""
        private var ph_nu: String = ""
        private var id_check: Int = 1
        private var pw_check: Int? = 0
        private val tele_list: Array<String> = arrayOf("KT", "SKT", "LGT")
        var idcheckbool = 0
        var nickcheckbool = 0


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) { }
            else {  }
        }
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)






        val but1 = si_ch as Button
        but1.setOnClickListener (object :View.OnClickListener{
            override fun onClick(p0: View?) {
                sid = si_id.text.toString()
                idcheckVolley(this@SingupActivity,sid){duplicatedNickname ->
                    if(duplicatedNickname){
                        Toast.makeText(this@SingupActivity, "중복된 ID입니다.", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@SingupActivity, "사용 가능한 ID입니다.", Toast.LENGTH_LONG).show()
                        idcheckbool=1
                    }

                }

            }
        })
        val butt1 = si_ni_ch as Button
        butt1.setOnClickListener (object : View.OnClickListener{
            override fun onClick(p0: View?) {
                nick=si_nick.text.toString()
                nickvolley.nickvolley.nickcheckVolley(this@SingupActivity,nick){duplicatedNickname ->
                    if(duplicatedNickname){
                        Toast.makeText(this@SingupActivity, "중복된 닉네임입니다.", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@SingupActivity, "사용 가능한 닉네임입니다.", Toast.LENGTH_LONG).show()
                        nickcheckbool=1
                    }
                }
            }
        })
        val but2 = si_pwch_bu as Button
        but2!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                pw = si_pw.text.toString()
                pwch = si_pwch.text.toString()


                if (pw.equals(pwch)) {

                    pw_check = 1
                    Toast.makeText(this@SingupActivity, "비밀번호와 비밀번호 확인이 같습니다.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@SingupActivity, "비밀번호와 비밀번호 확인이 다릅니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        val but3 = si_ve_ch as Button
        but3!!.setOnClickListener (object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    ph_nu= si_ve_nu.text.toString()
                    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(credential: PhoneAuthCredential) { }
                        override fun onVerificationFailed(e: FirebaseException) { }
                        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken){
                            this@SingupActivity.verificationId= verificationId

                        }
                    }

                    val optionsCompat = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+82"+ph_nu.substring(1))
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this@SingupActivity)
                        .setCallbacks(callbacks)
                        .build()

                    PhoneAuthProvider.verifyPhoneNumber(optionsCompat)
                    auth.setLanguageCode("kr")
                }
            })

        val but5 = si_ve_check as Button
        but5!!.setOnClickListener (object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    val credential = PhoneAuthProvider.getCredential(verificationId, si_ve_code.text.toString())
                    signInWithPhoneAuthCredential(credential)


                }
            })


        val but4 = si_com_bt as Button


        
        but4.setOnClickListener(object : View.OnClickListener {


                override fun onClick(p0: View?) {
                    if(nickcheckbool ==1 && idcheckbool ==1 && pw_check==1) {


                        signVolley(
                            this@SingupActivity,
                            sid,
                            nick,
                            pw,
                            ph_nu,
                            "2134243"
                        ) { success ->
                            if (success) {
                                Toast.makeText(this@SingupActivity, "!!", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(
                                    this@SingupActivity,
                                    "네트워크 연결을 확인해주세요",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    else{
                        Toast.makeText(this@SingupActivity, "중복확인과 비밀번호 확인을 먼저 체크해주세요", Toast.LENGTH_LONG).show()
                    }
                    }

            })

            }


        }


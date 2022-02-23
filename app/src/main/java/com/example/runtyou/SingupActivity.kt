package com.example.runtyou

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.runtyou.volleyService.VolleyService.testVolley
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

        private var sid: String = ""
        private var pw: String = ""
        private var pwch: String = ""
        private var ph_nu: String = ""
        private var id_check: Int = 1
        private var pw_check: Int? = 0
        private val tele_list: Array<String> = arrayOf("KT", "SKT", "LGT")







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)




        val adapters = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tele_list)
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        si_ve_sp.adapter = adapters
        si_ve_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var sele_item: String? = tele_list[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val but1 = si_ch as Button
        val but2 = si_pwch_bu as Button
        but2!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                sid = si_id.text.toString()
                pw = si_pw.text.toString()
                pwch = si_pwch.text.toString()
                ph_nu= si_ve_nu.text.toString()
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
        but3!!.setOnClickListener {
            object : View.OnClickListener {
                override fun onClick(p0: View?) {

                }
            }
        }

        val but4 = si_com_bt as Button
        but4.setOnClickListener {
            object : View.OnClickListener {

                override fun onClick(p0: View?) {
                    testVolley(this@SingupActivity,sid,"skybell",pw,ph_nu,"213443") { success ->
                        if (success) {
                            Toast.makeText(this@SingupActivity, "!!", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@SingupActivity, "네트워크 연결을 확인해주세요", Toast.LENGTH_LONG).show()
                        }
                    }
                    }

            }
                }
            }
    private fun testPhoneVerify() {
        // [START auth_test_phone_verify]
        val phoneNum = "+16505554567"
        val testVerificationCode = "123456"

        // Whenever verification is triggered with the whitelisted number,
        // provided it is not set for auto-retrieval, onCodeSent will be triggered.
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(phoneNum)
            .setTimeout(30L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onCodeSent(
                    verificationId: String,
                    forceResendingToken: PhoneAuthProvider.ForceResendingToken
                ) {
                    // Save the verification id somewhere
                    // ...

                    // The corresponding whitelisted code above should be used to complete sign-in.
                    this@SingupActivity.enableUserManuallyInputCode()
                }

                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    Toast.makeText(this@SingupActivity,"인증코드가 전송되었습니다. 60초 이내 입력해주세요",Toast.LENGTH_SHORT).show()

                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(this@SingupActivity,"인증에 실패했습니다. 다시 시도해주세요.",Toast.LENGTH_SHORT).show()

                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END auth_test_phone_verify]
    }
    private fun enableUserManuallyInputCode() {
        // No-op
    }
        }


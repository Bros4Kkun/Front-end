package com.example.runtyou

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_singup.*

class SingupActivity : AppCompatActivity() {

    private var id : String? = null
    private var pw : String? = null
    private var pwch : String? = null
    private var ph_nu : String? = null
    private var id_check : Int = 1
    private var pw_check : Int = 0
    private val tele_list : Array<String> = arrayOf("KT","SKT","LGT")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)
       val adapters = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,tele_list)
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        si_ve_sp.adapter = adapters
        si_ve_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var sele_item : String? = tele_list[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val but1 = si_ch as Button
        val but2 = si_pwch_bu as Button
        but2!!.setOnClickListener (object : View.OnClickListener{
            override fun onClick(p0: View?) {
                pw = si_pw.toString()
                pwch = si_pwch.toString()
                if(pw.equals(pwch)){
                    pw_check =1
                    Toast.makeText(this@SingupActivity,"비밀번호와 비밀번호 확인이 같습니다.",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@SingupActivity,"비밀번호와 비밀번호 확인이 다릅니다.",Toast.LENGTH_SHORT).show()
                }
            }
        })


    }
}

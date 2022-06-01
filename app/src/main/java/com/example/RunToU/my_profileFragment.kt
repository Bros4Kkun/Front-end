package com.example.RunToU

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_introduce.*

class my_profileFragment : Fragment() {
    lateinit var nick : TextView
    lateinit var intro : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainAC = context as MainActivity
        val rootView = inflater.inflate(R.layout.fragment_my_profile, container, false) as ViewGroup
        val btnIntroduce: Button
        val btnRevRec: Button
        val btnJobRec: Button
        val btnDetInfo: ImageButton
        val btnJob: ImageButton
        val btnMyList: ImageButton
        val btnSetting: ImageButton
        nick = rootView.findViewById(R.id.Nickname)


        btnIntroduce = rootView.findViewById(R.id.btnIntroduce)
        btnRevRec = rootView.findViewById(R.id.btnRevRec)
        btnJobRec = rootView.findViewById(R.id.btnJobRec)
        btnDetInfo = rootView.findViewById(R.id.btnDetInfo)
        btnJob = rootView.findViewById(R.id.btnJob)
        btnMyList = rootView.findViewById(R.id.btnMyList)
        btnSetting = rootView.findViewById(R.id.btnSetting)
        profileVolley.profileVolley(mainAC,chatRecieve.chatRecieve.loginID){
            if(it){

                println("it is profile")
                intro = rootView.findViewById(R.id.tvIntroduce)
                intro.text = profileVolley.selfintro
                nick.text = profileVolley.nick

            }
            else{
                println("it is profilenot")

            }
        }

        btnIntroduce.setOnClickListener {

            profileVolley.profileVolley(mainAC,chatRecieve.chatRecieve.loginID){
                if(it){
                    intro = rootView.findViewById(R.id.tvIntroduce)
                    intro.text = profileVolley.selfintro
                    val fragment = Fragment(R.layout.fragment_introduce) // 객체 생성
                    println("it is profile")
                    requireFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit()

                    btnIntroduce.setBackgroundColor(Color.parseColor("#6EB943"))
                    btnJobRec.setBackgroundColor(Color.WHITE)
                    btnRevRec.setBackgroundColor(Color.WHITE)
                    btnIntroduce.setTextColor(Color.WHITE)
                    btnJobRec.setTextColor(Color.BLACK)
                    btnRevRec.setTextColor(Color.BLACK)

                }
                else{
                    println("it is profilenot")

                }
            }

        }
        btnJobRec.setOnClickListener {
            val fragment = Fragment(R.layout.fragment_short_jobrec) // 객체 생성
            requireFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit()
            btnIntroduce.setBackgroundColor(Color.WHITE)
            btnJobRec.setBackgroundColor(Color.parseColor("#6EB943"))
            btnRevRec.setBackgroundColor(Color.WHITE)
            btnIntroduce.setTextColor(Color.BLACK)
            btnJobRec.setTextColor(Color.WHITE)
            btnRevRec.setTextColor(Color.BLACK)
        }
        btnRevRec.setOnClickListener {
            val fragment = Fragment(R.layout.fragment_revrec) // 객체 생성
            requireFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit()
            btnIntroduce.setBackgroundColor(Color.WHITE)
            btnJobRec.setBackgroundColor(Color.WHITE)
            btnRevRec.setBackgroundColor(Color.parseColor("#6EB943"))
            btnIntroduce.setTextColor(Color.BLACK)
            btnJobRec.setTextColor(Color.BLACK)
            btnRevRec.setTextColor(Color.WHITE)
        }
        btnJob.setOnClickListener {
            val intent = Intent(
                activity,
                jobrecActivity::class.java
            )
            startActivity(intent)
        }
        btnSetting.setOnClickListener {
            val intent = Intent(
                activity,
                settingActivity::class.java
            )
            startActivity(intent)
        }
        btnDetInfo.setOnClickListener {
            val intent = Intent(
                activity,
                edit_introduceActivity::class.java
            )
            startActivity(intent)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
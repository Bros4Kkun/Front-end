package com.example.RunToU

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_msg.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class MsgFragment : Fragment(){
    lateinit var mainAC: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context) //
        mainAC = context as MainActivity

         }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_msg, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val but1 = materialButton as Button?
        but1?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(activity,ChatRoomActivity2::class.java)
                startActivity(intent)
            }

        })
        val but2 = materialButton2 as Button?
        but2?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                chatroomVolley.chatroomVolley.chatroomVolley(mainAC){
                    it->
                    if(it){
                        if(chatRoomAdapter.bool){

                            val intent = Intent(activity,ChatRoomActivity2::class.java)
                            startActivity(intent)
                        }
                        else{
                            //채팅방이 있을 경우 연결
                        }
                    }
                }
            }

        })
    }


}
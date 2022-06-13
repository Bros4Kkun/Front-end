package com.example.RunToU.chat_match

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.RunToU.R
import com.example.RunToU.main.MainActivity
import com.example.RunToU.volley.chatlistVolley
import kotlinx.android.synthetic.main.fragment_msg.*
import java.util.*

class msgFragment : Fragment(){
    companion object {
        var datalist = ArrayList<chatlistData>()
    }
    lateinit var mainAC: MainActivity
    var chatlistAdpater = chatlistAdapter(datalist)
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
        datalist.clear()
        chatlistVolley.chatlistVolley.chatroomExsitVolley(requireContext()){
            it->
            if(it){
                recyvlerv.layoutManager= LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyvlerv.adapter = chatlistAdpater
            }
            else
            {
                Toast.makeText(requireContext(), "네트워크 연결이 좋지 않습니다. 다시 시도해 주세요", Toast.LENGTH_SHORT).show()
            }
        }




       /*val but2 = materialButton2 as Button?
        but2?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                chatroomVolley.chatroomVolley.chatroomVolley(mainAC){
                   it->
                    if(it){
                       if(chatRoomAdapter.bool){

                            Stompclass.Stomclass.connect("/topic/chatroom/",chatRoomAdapter.chRo)
                            val intent = Intent(activity,ChatRoomActivity2::class.java)
                            startActivity(intent)

                        }
                        else{
                            //채팅방이 있을 경우 연결
                        }
                    }
                }
            }

        })*/

    }



}
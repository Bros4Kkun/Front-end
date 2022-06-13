package com.example.RunToU.chat_match

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.RunToU.R
import de.hdodenhof.circleimageview.CircleImageView

class chatlistAdapter(datalist: ArrayList<chatlistData>) :RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var myDatalist: ArrayList<chatlistData>? = null
    var id35 : String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.msg_list,parent,false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if(viewHolder is ViewHolder) {
            if(myDatalist!![position].or_nick== chatRecieve.chatRecieve.loginID){
                id35 = myDatalist!![position].pe_nick
                viewHolder.textv_nickname.text = myDatalist!![position].pe_nick}

            else{
                 id35 = myDatalist!![position].or_nick
                viewHolder.textv_nickname.text = myDatalist!![position].or_nick
            }
                viewHolder.textv_current.text = "진행 중인 심부름"
                viewHolder.textv_msg.text = myDatalist!![position].lastmessage
                viewHolder.textv_time.text = myDatalist!![position].pe_nick
            viewHolder.itemView.setOnClickListener{
                val intent =Intent(viewHolder.itemView.context, chatroomActivity::class.java)
                if(myDatalist!![position].or_nick== chatRecieve.chatRecieve.loginID){
                    listtochatData.nicksend = myDatalist!![position].pe_nick}
                else{
                    listtochatData.nicksend =myDatalist!![position].or_nick
                }
                listtochatData.chatindex = myDatalist!![position].index
                println(listtochatData.chatindex.toString()+"qwer")
                ContextCompat.startActivity(viewHolder.itemView.context,intent, null )
            }


}

    }
    override fun getItemCount(): Int {
        return myDatalist!!.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var imgv: CircleImageView
        var textv_nickname: TextView
        var textv_current: TextView
        var textv_msg: TextView
        var textv_time : TextView

        init {
            imgv = itemView.findViewById<View>(R.id.imageIcon) as CircleImageView
            textv_nickname = itemView.findViewById<View>(R.id.name_tv) as TextView
            textv_current = itemView.findViewById<View>(R.id.current_tv) as TextView
            textv_msg = itemView.findViewById<View>(R.id.content_tv) as TextView
            textv_time = itemView.findViewById<View>(R.id.time_tv) as TextView

        }
        fun bind(){

        }

    }

    init {
        myDatalist = datalist
    }

    }

package com.example.RunToU.chat_match

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.RunToU.R
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class ChatAdpater(nickname : String, dataList: ArrayList<chatData>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    private var myDatalist: ArrayList<chatData>? = null
    private var check = nickname
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return if (viewType == chatmsgstyleCode.ViewType.CENTER_CONTENT) {
            view = inflater.inflate(R.layout.room_center_list_items, parent, false)
            CenterViewHolder(view)
        } else if (viewType == chatmsgstyleCode.ViewType.LEFT_CONTENT) {
            view = inflater.inflate(R.layout.room_left_list_items, parent, false)
            LeftViewHolder(view)
        } else {
            view = inflater.inflate(R.layout.room_right_list_items, parent, false)
            RightViewHolder(view)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is CenterViewHolder) {
            viewHolder.textv.text = myDatalist!![position].getContent()
        } else if (viewHolder is LeftViewHolder) {
            if(viewHolder.textv_nickname.text != check) {
                viewHolder.textv_nickname.text = myDatalist!![position].getName()
                viewHolder.textv_msg.text = myDatalist!![position].getContent()
                viewHolder.textv_time.text = myDatalist!![position].datetime()
            }
        } else {
            (viewHolder as RightViewHolder).textv_msg.text = myDatalist!![position].getContent()
        }

    }

    override fun getItemCount(): Int {
        return myDatalist!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return myDatalist!![position].getViewType()
    }

    inner class CenterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textv: TextView

        init {
            textv = itemView.findViewById<View>(R.id.textv) as TextView
        }
    }

    inner class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgv: CircleImageView
        var textv_nickname: TextView
        var textv_msg: TextView
        var textv_time: TextView

        init {
            imgv = itemView.findViewById<View>(R.id.imgv) as CircleImageView
            textv_nickname = itemView.findViewById<View>(R.id.textv_nicname1) as TextView
            textv_msg = itemView.findViewById<View>(R.id.textv_msg1) as TextView
            textv_time = itemView.findViewById<View>(R.id.textv_time1) as TextView
        }
    }

    inner class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textv_msg: TextView
        var textv_time: TextView

        init {
            textv_msg = itemView.findViewById<View>(R.id.textv_msg) as TextView
            textv_time = itemView.findViewById<View>(R.id.textv_time) as TextView
        }
    }



    init {
        myDatalist = dataList
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
}
package com.example.RunToU

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class ExpandableListAdapter(val data: MutableList<Item>): RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        var view: View? = null
        val context = parent.context
        when (type) {
            ExpandableListAdapter.HEADER -> {
                val inflaterHeader =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflaterHeader.inflate(R.layout.list_header, parent, false)
                return ExpandableListAdapter.ListHeaderViewHolder(view)
            }
            ExpandableListAdapter.CHILD -> {
                val inflaterChild =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflaterChild.inflate(R.layout.list_child, parent, false)
                return ExpandableListAdapter.ListChildViewHolder(view)
            }
        }
        return ExpandableListAdapter.ListHeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        when (item!!.type) {
            ExpandableListAdapter.HEADER -> {
                val itemController = holder as ExpandableListAdapter.ListHeaderViewHolder?
                itemController!!.refferalItem = item
                itemController.header_title.text = item.text
                if (item.invisibleChildren == null) {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_arrow_upward_black_24dp)
                } else {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_arrow_downward_black_24dp)
                }
                itemController.btn_expand_toggle.setOnClickListener {
                    if (item.invisibleChildren == null) {
                        item.invisibleChildren = ArrayList()
                        var count = 0
                        val pos = data.indexOf(itemController.refferalItem)
                        while (data.size > pos + 1 && data[pos + 1]!!.type == ExpandableListAdapter.CHILD) {
                            (item.invisibleChildren as ArrayList<Item?>).add(data.removeAt(pos + 1))
                            count++
                        }
                        notifyItemRangeRemoved(pos + 1, count)
                        itemController.btn_expand_toggle.setImageResource(R.drawable.ic_arrow_downward_black_24dp)
                    } else {
                        val pos = data.indexOf(itemController.refferalItem!!)
                        var index = pos + 1
                        for (i in item.invisibleChildren!!) {
                            if (i != null) {
                                data.add(index, i)
                            }
                            index++
                        }
                        notifyItemRangeInserted(pos + 1, index - pos - 1)
                        itemController.btn_expand_toggle.setImageResource(R.drawable.ic_arrow_upward_black_24dp)
                        item.invisibleChildren = null
                    }
                }
            }
            ExpandableListAdapter.CHILD -> {
                val itemController1 = holder as ExpandableListAdapter.ListChildViewHolder?
                itemController1!!.refferalItem = item
                itemController1.child_title.text = item.text
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return data[position]!!.type
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private class ListHeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var header_title: TextView
        var btn_expand_toggle: ImageView
        var refferalItem: Item? = null

        init {
            header_title = itemView!!.findViewById<View>(R.id.header_title) as TextView
            btn_expand_toggle = itemView.findViewById<View>(R.id.btn_expand_toggle) as ImageView
        }
    }

    private class ListChildViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var child_title: TextView
        var btn: Button
        var refferalItem: Item? = null

        init {
            child_title = itemView!!.findViewById<View>(R.id.child_title) as TextView
            btn = itemView.findViewById(R.id.btn)

        }
    }

    class Item {
        var type = 0
        var text: String? = null
        var invisibleChildren: MutableList<Item?>? = null

        constructor() {}
        constructor(type: Int, text: String?) {
            this.type = type
            this.text = text
        }
    }

    companion object {
        const val HEADER = 0
        const val CHILD = 1
    }
}
package com.example.mymoneybook.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoneybook.R
import kotlinx.android.synthetic.main.memo_item.view.*

class MemoAdapter(val db : MemoDatabase, val memo_data :MutableList<Memo>, val context: Context
                  , val itemClick : (Memo) -> Unit) :
    RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.memo_item,parent,false)

        val holder = MemoViewHolder(view,itemClick)

        return holder
    }

    override fun getItemCount(): Int {
        return memo_data.size
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {

        val viewHolder = holder.itemView

        viewHolder.memo_title.text = memo_data[position].title
        viewHolder.memo_body.text = memo_data[position].body

        holder.bind(memo_data[position],context)

    }

    inner class MemoViewHolder(itemView : View,itemClick: (Memo) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bind(item : Memo, context: Context){
            itemView.setOnClickListener { itemClick(item) }
        }

    }
}
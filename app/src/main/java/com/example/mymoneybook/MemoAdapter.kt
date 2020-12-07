package com.example.mymoneybook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemoAdapter(val db : MemoDatabase,val memo_data :MutableList<Memo>,val context: Context
,val itemClick : (Memo) -> Unit) :
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

        holder.txtMemoTitle.text = memo_data[position].title
        holder.txtMemoBody.text = memo_data[position].body

        holder.bind(memo_data[position],context)

    }

    inner class MemoViewHolder(itemView : View,itemClick: (Memo) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val txtMemoTitle = itemView.findViewById<TextView>(R.id.memo_title)
        val txtMemoBody = itemView.findViewById<TextView>(R.id.memo_body)

        fun bind(item : Memo,context: Context){
            itemView.setOnClickListener { itemClick(item) }
        }

    }
}
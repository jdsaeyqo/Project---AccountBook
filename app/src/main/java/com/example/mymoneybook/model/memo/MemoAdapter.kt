package com.example.mymoneybook.model.memo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoneybook.R
import kotlinx.android.synthetic.main.memo_item.view.*

class MemoAdapter(val itemClick : (Memo) -> Unit) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

    private var memoList : List<Memo> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memo_item,parent,false)

        val holder = MemoViewHolder(view,itemClick)

        return holder
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {

        val viewHolder = holder.itemView

        viewHolder.memo_title.text = memoList[position].title
        viewHolder.memo_body.text = memoList[position].body

        holder.bind(memoList[position])

    }

    inner class MemoViewHolder(itemView : View,itemClick: (Memo) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bind(item : Memo){
            itemView.setOnClickListener { itemClick(item) }
        }

    }

    fun setMemo(memoList : List<Memo>){
        this.memoList = memoList
        notifyDataSetChanged()
    }
}
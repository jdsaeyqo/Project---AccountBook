package com.example.mymoneybook.data

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoneybook.R
import kotlinx.android.synthetic.main.data_item.view.*
import java.text.DecimalFormat


class DataAdapter(val itemClick: (Data)-> Unit) : RecyclerView.Adapter<DataAdapter.MViewHolder>(){

    private var dataList : List<Data> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        val holder = MViewHolder(view)


        return holder
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {

        val decimal = DecimalFormat("###,###")
        val viewholder = holder.itemView


        viewholder.txt_date.text = dataList[position].date
        viewholder.txt_sep.text = dataList[position].sep
        viewholder.txt_checked.text = dataList[position].checked

        if(dataList[position].money != ""){
            viewholder.txt_money.text = decimal.format(Integer.parseInt(dataList[position].money!!)).toString()
        }
        if (viewholder.txt_checked.text == "수입") {
            viewholder.txt_money.setTextColor(Color.BLUE)
        } else {
            viewholder.txt_money.setTextColor(Color.RED)
        }

        viewholder.txt_purp.text = dataList[position].purp

        holder.bind(dataList[position])

    }

    inner class MViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){


        fun bind(item: Data) {
            itemView.setOnClickListener { itemClick(item) }


        }


    }

    fun setData(dataList : List<Data>){
        this.dataList = dataList
        notifyDataSetChanged()
    }



}
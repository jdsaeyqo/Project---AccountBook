package com.example.mymoneybook.data

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoneybook.R
import kotlinx.android.synthetic.main.data_item.view.*
import java.text.DecimalFormat


class DataAdapter(val db : MoneyDatabase, val data : MutableList<Data>, val context: Context
                  , val itemClick: (Data)-> Unit)
    : RecyclerView.Adapter<DataAdapter.mViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.data_item, parent, false)
        val holder = mViewHolder(view, itemClick)


        return holder
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {

        val decimal = DecimalFormat("###,###")
        val viewholder = holder.itemView


        viewholder.txt_date.text = data[position].date
        viewholder.txt_sep.text = data[position].sep
        viewholder.txt_checked.text = data[position].checked
        if(data[position].money != ""){
            viewholder.txt_money.text = decimal.format(Integer.parseInt(data[position].money!!)).toString()
        }
        if (viewholder.txt_checked.text == "수입") {
            viewholder.txt_money.setTextColor(Color.BLUE)
        } else {
            viewholder.txt_money.setTextColor(Color.RED)
        }

        viewholder.txt_purp.text = data[position].purp

        holder.bind(data[position], context)


    }


    inner class mViewHolder(itemView: View, itemClick: (Data) -> Unit) :
        RecyclerView.ViewHolder(itemView){


        fun bind(item: Data, context: Context) {
            itemView.setOnClickListener { itemClick(item) }


        }


    }



}
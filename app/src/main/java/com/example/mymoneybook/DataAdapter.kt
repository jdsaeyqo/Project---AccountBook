package com.example.mymoneybook

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat


class DataAdapter(val db : MoneyDatabase,val data : MutableList<Data>,val context: Context
,val itemClick: (Data)-> Unit)
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


        holder.txtDate.text = data[position].date
        holder.txtSep.text = data[position].sep
        holder.txtChecked.text = data[position].checked
        if(data[position].money != ""){
        holder.txtMoney.text = decimal.format(Integer.parseInt(data[position].money!!)).toString()
        }
        if (holder.txtChecked.text == "수입") {
            holder.txtMoney.setTextColor(Color.BLUE)
        } else {
            holder.txtMoney.setTextColor(Color.RED)
        }

        holder.txtPurp.text = data[position].purp

        holder.bind(data[position], context)


    }


    inner class mViewHolder(itemView: View, itemClick: (Data) -> Unit) :
        RecyclerView.ViewHolder(itemView){
        val txtDate: TextView = itemView.findViewById<TextView>(R.id.txt_date)
        val txtSep = itemView.findViewById<TextView>(R.id.txt_sep)
        val txtMoney = itemView.findViewById<TextView>(R.id.txt_money)
        val txtPurp = itemView.findViewById<TextView>(R.id.txt_purp)
        val txtChecked = itemView.findViewById<TextView>(R.id.txt_checked)


        fun bind(item: Data, context: Context) {
            itemView.setOnClickListener { itemClick(item) }


        }


    }



}
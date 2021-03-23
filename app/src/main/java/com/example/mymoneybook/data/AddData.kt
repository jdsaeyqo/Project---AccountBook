package com.example.mymoneybook.data

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import com.example.mymoneybook.R
import kotlinx.android.synthetic.main.activity_add_data.*

class AddData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)


        val calender: CalendarView = calendar.findViewById(R.id.calendar)
        calender.setOnDateChangeListener { view, year, month, dayOfMonth ->

            text_date.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)

            btn_save.setOnClickListener {

                var checked: String = if (radio_income.isChecked){
                    "수입"
                }else{
                    "지출"
                }

                val data = Data(
                    id = 0,
                    date = text_date.text.toString(),
                    sep = edit_sep.text.toString(),
                    money = edit_money.text.toString(),
                    purp = edit_purp.text.toString(),
                    checked = checked
                )


                val resultIntent = Intent()
                resultIntent.putExtra("data",data)

                setResult(Activity.RESULT_OK,resultIntent)
                finish()

            }

        }
    }}



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
            var year = year
            var month = month
            var day = dayOfMonth

            text_date.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)


            btn_save.setOnClickListener {

                val checked: String
                if (radio_income.isChecked){
                     checked = "수입"
                }else{
                    checked = "지출"
                }

                val finaldate = text_date.text.toString()
                val finalsep = edit_sep.text.toString()
                val finalmoney = edit_money.text.toString()
                val finalpurp = edit_purp.text.toString()

                val data = Data()
                data.date = finaldate
                data.sep = finalsep
                data.money = finalmoney
                data.purp = finalpurp
                data.checked = checked


                val result_intent = Intent()
                result_intent.putExtra("data",data)

                setResult(Activity.RESULT_OK,result_intent)
                finish()

            }

        }
    }}



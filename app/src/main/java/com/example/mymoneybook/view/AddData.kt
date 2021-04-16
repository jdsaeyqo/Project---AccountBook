package com.example.mymoneybook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import androidx.activity.viewModels
import com.example.mymoneybook.R
import com.example.mymoneybook.model.Data
import com.example.mymoneybook.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_add_data.*

class AddData : AppCompatActivity() {

    private val dataViewModel: MainViewModel by viewModels()
    private var id:Long? =null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)


        val calender: CalendarView = calendar.findViewById(R.id.calendar)
        calender.setOnDateChangeListener { view, year, month, dayOfMonth ->

            text_date.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)

            btn_save.setOnClickListener {

                val checked: String = if (radio_income.isChecked){
                    "수입"
                }else{
                    "지출"
                }

                val data = Data(
                    id = id,
                    date = text_date.text.toString(),
                    sep = edit_sep.text.toString(),
                    money = edit_money.text.toString(),
                    purp = edit_purp.text.toString(),
                    checked = checked
                )

                dataViewModel.insert(data)

                finish()


            }

        }
    }}



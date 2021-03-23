package com.example.mymoneybook.main

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoneybook.R
import com.example.mymoneybook.data.AddData
import com.example.mymoneybook.data.Data
import com.example.mymoneybook.data.DataAdapter
import com.example.mymoneybook.data.MoneyDatabase
import kotlinx.android.synthetic.main.frag_all.*
import java.text.DecimalFormat


class ShowAll : Fragment() {

    val decimal = DecimalFormat("###,###")

    var income = 0
    var outcome = 0
    var result = 0

    var db: MoneyDatabase? = null

    lateinit var adapter: DataAdapter

    private var data_list = mutableListOf<Data>()

    val ADDdataActivity = 0

    private lateinit var recyclerview: RecyclerView


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.frag_all, container, false)

        db = MoneyDatabase.getInstance(activity!!)
        recyclerview = view.findViewById(R.id.recycle_all)
        recyclerview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


        val r = Runnable {

            val savedContacts = db!!.dataDao().getAll()
            if (savedContacts.isNotEmpty()) {
                data_list.addAll(savedContacts)

            }

            refreshMoney()

            result = income - outcome

            activity!!.runOnUiThread {
                toDecimal()
            }

            adapter = DataAdapter(
                db!!,
                data_list,
                activity!!
            ) { Data ->

                val popupMenu = PopupMenu(
                    activity,
                    recycle_all,
                    Gravity.NO_GRAVITY,
                    R.attr.actionOverflowMenuStyle,
                    0
                )
                val menuInflater = MenuInflater(activity)
                menuInflater.inflate(R.menu.menu, popupMenu.menu)

//

                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_delete -> {
                            val v = Runnable {

                                db?.dataDao()?.delete(Data)
                                data_list.remove(Data)
                                activity!!.runOnUiThread {
                                    adapter.notifyDataSetChanged()

                                    initMoney()
                                    refreshMoney()


                                    result = income - outcome
                                    toDecimal()


                                }
                            }
                            Thread(v).start()
                            true

                        }
                        else -> {
                            val r = Runnable {
                                db?.dataDao()?.deleteAll()
                                data_list.clear()
                                activity!!.runOnUiThread {
                                    adapter.notifyDataSetChanged()
                                    //초기화
                                    initMoney()

                                }
                            }
                            Thread(r).start()
                            true
                        }

                    }
                }
                popupMenu.show()

            }
            activity!!.runOnUiThread {
                adapter.notifyDataSetChanged()
                recyclerview.adapter = adapter
            }

        }
        Thread(r).start()

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {
            val intent = Intent(activity, AddData::class.java)
            startActivityForResult(intent, ADDdataActivity)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADDdataActivity) {
            if (resultCode == Activity.RESULT_OK) {

                val info = data?.getParcelableExtra<Data>("data")

                val newData = Data(
                    id = 1,
                    date = info?.date,
                    sep = info?.sep,
                    money = info?.money,
                    purp = info?.purp,
                    checked = info?.checked
                )





                Thread { db!!.dataDao().insert(newData) }.start()

                data_list.add(newData)

                recyclerview.adapter?.notifyDataSetChanged()
                if (newData.checked == "수입") {
                    income += Integer.parseInt(newData.money)
                } else {
                    if (newData.money != "") {
                        outcome += Integer.parseInt(newData.money)
                    }
                }
                result = income - outcome

                toDecimal()

            }


        }

    }

    private fun toDecimal() {

        textIncome.text = decimal.format(income).toString()
        textOutcome.text = decimal.format(outcome).toString()
        textResult.text = decimal.format(result).toString()
    }

    private fun refreshMoney() {

        for (x in 0..(data_list.size - 1)) {
            if (data_list[x].checked == "수입") {
                income += Integer.parseInt(data_list[x].money.toString())
            } else {
                if (data_list[x].money.toString() != "") {
                    outcome += Integer.parseInt(data_list[x].money.toString())
                }

            }
        }
    }

    private fun initMoney() {
        income = 0
        outcome = 0
        result = 0
    }

}

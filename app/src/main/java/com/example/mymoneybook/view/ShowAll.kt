package com.example.mymoneybook.view


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoneybook.viewmodel.MainViewModel
import com.example.mymoneybook.R
import com.example.mymoneybook.model.Data
import com.example.mymoneybook.model.DataAdapter
import kotlinx.android.synthetic.main.frag_all.*
import java.text.DecimalFormat


class ShowAll : Fragment() {

    private val dataViewModel : MainViewModel by activityViewModels()

    private val decimal = DecimalFormat("###,###")
    var income =  0
    var outcome = 0
    var result  = 0

    lateinit var adapter: DataAdapter

    lateinit var popupMenu: PopupMenu

    private lateinit var recyclerview: RecyclerView

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.frag_all, container, false)


        recyclerview = view.findViewById(R.id.recycle_all)

        adapter = DataAdapter() { Data ->
            itemClick(Data)

        }

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        recyclerview.setHasFixedSize(true)

        dataViewModel.getAll()?.observe(viewLifecycleOwner,Observer<List<Data>>{
            adapter.setData(it)
            setResultText(it)

        })

        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {
            val intent = Intent(activity, AddData::class.java)
            startActivity(intent)
        }

    }

    private fun itemClick(data : Data) {
        showPopUp()
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_delete -> {
                    dataViewModel.delete(data)
                    true

                }
                else -> {
                    dataViewModel.deleteAll()
                    true
                }

            }
        }
        popupMenu.show()
    }


    private fun showPopUp() {
        popupMenu = PopupMenu(
            activity,
            recycle_all,
            Gravity.NO_GRAVITY,
            R.attr.actionOverflowMenuStyle,
            0
        )
        val menuInflater = MenuInflater(activity)
        menuInflater.inflate(R.menu.menu, popupMenu.menu)

    }

    private fun setResultText(data : List<Data>) {

        income = 0
        outcome = 0
        result = 0

        for (i in data.indices){

            if(data[i].checked == "수입"){
                income += data[i].money.toString().toInt()
            }else{
                outcome += data[i].money.toString().toInt()
            }

        }

        result = income - outcome

        textIncome.text = decimal.format(income).toString()
        textOutcome.text = decimal.format(outcome).toString()
        textResult.text = decimal.format(result).toString()
    }


}

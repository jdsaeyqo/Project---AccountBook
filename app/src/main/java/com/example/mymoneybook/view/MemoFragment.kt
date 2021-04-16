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
import com.example.mymoneybook.R
import com.example.mymoneybook.model.memo.Memo
import com.example.mymoneybook.model.memo.MemoAdapter
import com.example.mymoneybook.viewmodel.MemoViewModel
import kotlinx.android.synthetic.main.frag_memo.*

class MemoFragment : Fragment() {


    private val memoViewModel: MemoViewModel by activityViewModels()
    lateinit var popupMenu: PopupMenu

    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: MemoAdapter


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.frag_memo, container, false)

        recyclerview = view.findViewById(R.id.recycle_memo)

        adapter = MemoAdapter() { Memo ->
            itemClick(Memo)

        }

        recyclerview.adapter = adapter
        recyclerview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerview.setHasFixedSize(true)

        memoViewModel.getAll()?.observe(viewLifecycleOwner, Observer<List<Memo>> {
            adapter.setMemo(it)

        })


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_memoadd.setOnClickListener {
            val intent = Intent(activity, AddMemo::class.java)
            startActivity(intent)
        }
    }

    private fun itemClick(memo: Memo) {
        showPopUp()

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_delete -> {
                    memoViewModel.delete(memo)
                    true
                }
                else -> {

                    memoViewModel.deleteAll()
                    true
                }
            }
        }

        popupMenu.show()

    }



    private fun showPopUp() {
        popupMenu = PopupMenu(
            activity,
            recycle_memo,
            Gravity.NO_GRAVITY,
            R.attr.actionOverflowMenuStyle,
            0
        )
        val menuInflater = MenuInflater(activity)
        menuInflater.inflate(R.menu.menu, popupMenu.menu)

    }


}

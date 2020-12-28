package com.example.mymoneybook.memo

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoneybook.R
import kotlinx.android.synthetic.main.frag_memo.*



class MemoFragment : Fragment() {

    var db : MemoDatabase? = null

    val AddMemoActivity = 0

    private lateinit var recyclerview: RecyclerView
    private var memo_list = mutableListOf<Memo>()
    private lateinit var adapter: MemoAdapter


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.frag_memo, container, false)

        recyclerview=view.findViewById(R.id.recycle_memo)
        recyclerview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)




        db = MemoDatabase.getInstance(activity!!)


        val r = Runnable {
            val savedContacts = db!!.memoDao().getAll()
            if(savedContacts.isNotEmpty()){
                memo_list.addAll(savedContacts)
            }
            adapter = MemoAdapter(
                db!!,
                memo_list,
                activity!!
            ) { Memo ->

                val popupMenu = PopupMenu(
                    activity,
                    recycle_memo,
                    Gravity.NO_GRAVITY,
                    R.attr.actionOverflowMenuStyle,
                    0
                )
                val menuInflater = MenuInflater(activity)
                menuInflater.inflate(R.menu.menu, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_delete -> {
                            val v = Runnable {
                                db?.memoDao()?.delete(Memo)
                                memo_list.remove(Memo)
                                activity!!.runOnUiThread {
                                    adapter.notifyDataSetChanged()
                                }
                            }
                            Thread(v).start()
                            true
                        }
                        else -> {

                            val r = Runnable {
                                db?.memoDao()?.deleteAll()
                                memo_list.clear()
                                activity!!.runOnUiThread {
                                    adapter.notifyDataSetChanged()
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
                recyclerview.adapter = adapter
            }

       }
       Thread(r).start()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_memoadd.setOnClickListener {
            val intent = Intent(activity, AddMemo::class.java)
            startActivityForResult(intent,AddMemoActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == AddMemoActivity){
            if(resultCode == Activity.RESULT_OK){

                val memo = data?.getParcelableExtra<Memo>("memo")
                val NewMemo = Memo(
                    0,
                    memo!!.title,
                    memo!!.body
                )

                Thread(Runnable { db!!.memoDao().insert(NewMemo) }).start()

                memo_list.add(NewMemo)
                recyclerview.adapter?.notifyDataSetChanged()
            }
        }
    }



}

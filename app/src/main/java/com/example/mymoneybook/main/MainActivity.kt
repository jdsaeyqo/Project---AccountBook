package com.example.mymoneybook.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mymoneybook.R
import com.example.mymoneybook.memo.MemoFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :FragmentActivity() {

    // ViewPager2에 세팅하기 위한 Fragment들을 가지고 있는 ArrayList
    private val fragmemtList = ArrayList<Fragment>()


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragAll = ShowAll()
        val fragMemo = MemoFragment()

        fragmemtList.add(fragAll)
        fragmemtList.add(fragMemo)



        val adapter1 = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragmemtList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmemtList[position]
            }
        }
        pager2.adapter = adapter1


        TabLayoutMediator(tabs, pager2) { tab, position ->

       }.attach()
        tabs.getTabAt(0)?.setIcon(R.drawable.baseline1_money_black_18dp)
        tabs.getTabAt(1)?.setIcon(R.drawable.baseline1_notes_black_18dp)

    }

}

package com.example.mymoneybook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mymoneybook.R
import com.example.mymoneybook.model.Memo
import com.example.mymoneybook.viewmodel.MemoViewModel
import kotlinx.android.synthetic.main.activity_add_memo.*

class AddMemo : AppCompatActivity() {

    private val memoViewModel : MemoViewModel by viewModels()
    private var id:Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_memo)


        btn_memosave.setOnClickListener {

            val memoTitle = memo_title.text.toString()
            val memoBody = memo_body.text.toString()

            val memo = Memo(id, memoTitle, memoBody)

            memoViewModel.insert(memo)
            finish()

        }



    }
}
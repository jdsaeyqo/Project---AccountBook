package com.example.mymoneybook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_memo.*

class AddMemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_memo)


        btn_memosave.setOnClickListener {
            val MemoTitle = memo_title.text.toString()
            val MemoBody = memo_body.text.toString()

            val memo = Memo(0,MemoTitle,MemoBody)

            val result_intent = Intent()
            result_intent.putExtra("memo",memo)

            setResult(Activity.RESULT_OK,result_intent)
            finish()




        }



    }
}
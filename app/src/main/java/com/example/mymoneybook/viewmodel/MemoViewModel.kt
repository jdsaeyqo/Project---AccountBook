package com.example.mymoneybook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mymoneybook.model.memo.Memo
import com.example.mymoneybook.model.memo.MemoRepository

class MemoViewModel(application: Application): AndroidViewModel(application){

    private val repository = MemoRepository(application)
    private var memos = repository.getAll()

    fun getAll() : LiveData<List<Memo>>? {
        return this.memos
    }

    fun insert(memo: Memo){
        repository.insert(memo)
    }

    fun delete(memo: Memo){
        repository.delete(memo)
    }

    fun deleteAll(){
        repository.deleteAll()
    }
}
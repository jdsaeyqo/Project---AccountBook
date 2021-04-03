package com.example.mymoneybook.memo

import android.app.Application
import androidx.lifecycle.LiveData

class MemoRepository(application: Application) {

    private val memoDatabase = MemoDatabase.getInstance(application)
    private val memoDao : MemoDao? = memoDatabase?.memoDao()
    private val memos : LiveData<List<Memo>>? = memoDao?.getAll()

    fun getAll() : LiveData<List<Memo>>?{
        return memos

    }

    fun insert(memo : Memo){
        try {
            val thread =Thread(Runnable {
                memoDao?.insert(memo)
            })
            thread.start()
        }
        catch (e: Exception){

        }
    }

  fun delete(memo : Memo){
        try {
            val thread =Thread(Runnable {
                memoDao?.delete(memo)
            })

            thread.start()
        }catch (e : Exception){

        }

    }

  fun deleteAll(){
        try {
            val thread =Thread(Runnable {
                memoDao?.deleteAll()
            })
        }catch (e : Exception){

        }
    }


}
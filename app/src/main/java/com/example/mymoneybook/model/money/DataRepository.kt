package com.example.mymoneybook.model.money

import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception

class DataRepository(application: Application) {

    private val moneyDatabase = MoneyDatabase.getInstance(application)
    private val dataDao : DataDao? = moneyDatabase?.dataDao()
    private val datas : LiveData<List<Data>>? = dataDao?.getAll()

    fun getAll() : LiveData<List<Data>>? {
        return datas
    }

    fun insert(data: Data){
        try {
            val thread = Thread(Runnable {
                dataDao?.insert(data)
            })
            thread.start()
        }catch (e : Exception){

        }
    }

    fun delete(data: Data){
        try{
            val thread = Thread(Runnable {
                dataDao?.delete(data)
            })
            thread.start()
        }catch (e : Exception){

        }
    }

    fun deleteAll(){
        try{
            val thread = Thread(Runnable {
                dataDao?.deleteAll()
            })
            thread.start()
        }catch (e : Exception){

        }
    }

}
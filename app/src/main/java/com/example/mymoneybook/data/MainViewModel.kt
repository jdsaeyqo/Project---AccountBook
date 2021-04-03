package com.example.mymoneybook.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository = DataRepository(application)
    private val datas = repository.getAll()

    fun getAll() : LiveData<List<Data>>? {
        return this.datas
    }

    fun insert(data: Data){
        repository.insert(data)
    }

    fun delete(data : Data){
        repository.delete(data)
    }

    fun deleteAll(){
        repository.deleteAll()
    }


}
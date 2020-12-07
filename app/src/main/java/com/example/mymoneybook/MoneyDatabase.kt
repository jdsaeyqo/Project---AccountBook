package com.example.mymoneybook

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Data::class),version = 1,exportSchema = false)
abstract class MoneyDatabase:RoomDatabase() {
    abstract fun dataDao():DataDao

    companion object{
        private var INSTANCE : MoneyDatabase? = null
        fun getInstance(context : Context):MoneyDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MoneyDatabase::class.java,
                    "money_database")
                    .build()

            }
            return INSTANCE as MoneyDatabase
        }
    }
}
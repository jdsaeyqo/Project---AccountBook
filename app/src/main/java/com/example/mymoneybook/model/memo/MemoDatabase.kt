package com.example.mymoneybook.model.memo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Memo::class],version = 1,exportSchema = false)
abstract class MemoDatabase : RoomDatabase() {
   abstract fun memoDao(): MemoDao

   companion object{
      private var INSTANCE : MemoDatabase? = null
      fun getInstance(context : Context): MemoDatabase? {
         if(INSTANCE == null){

            INSTANCE = Room.databaseBuilder(
               context.applicationContext,
               MemoDatabase::class.java,
               "memo_database")
               .fallbackToDestructiveMigration()
               .build()

         }
         return INSTANCE
      }
   }
}
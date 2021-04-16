package com.example.mymoneybook.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MemoDao {
    @Query("SELECT * from MemoTable")
    fun getAll():LiveData<List<Memo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo: Memo)

    @Update
    fun update(memo: Memo)

    @Delete
    fun delete(memo: Memo)

    @Query("DELETE FROM MemoTable")
    fun deleteAll()
}

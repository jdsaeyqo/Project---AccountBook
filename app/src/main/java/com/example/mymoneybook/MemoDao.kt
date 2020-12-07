package com.example.mymoneybook

import androidx.room.*

@Dao
interface MemoDao {
    @Query("SELECT * from MemoTable")
    fun getAll():List<Memo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo: Memo)

    @Update
    fun update(memo: Memo)

    @Delete
    fun delete(memo: Memo)

    @Query("DELETE FROM MemoTable")
    fun deleteAll()
}

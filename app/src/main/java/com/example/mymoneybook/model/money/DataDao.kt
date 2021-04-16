package com.example.mymoneybook.model.money

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataDao {
    @Query("SELECT * from moneybookTable ")
    fun getAll():LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data : Data)

    @Update
    fun update(data: Data)

    @Delete
    fun delete(data: Data)

    @Query("DELETE FROM moneybookTable")
    fun deleteAll()
}
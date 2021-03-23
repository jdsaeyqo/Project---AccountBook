package com.example.mymoneybook.data

import androidx.room.*

@Dao
interface DataDao {
    @Query("SELECT * from moneybookTable ")
    fun getAll():List<Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data : Data)

    @Update
    fun update(data: Data)

    @Delete
    fun delete(data: Data)

    @Query("DELETE FROM moneybookTable")
    fun deleteAll()
}
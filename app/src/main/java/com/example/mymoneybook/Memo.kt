package com.example.mymoneybook

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "MemoTable")
data class Memo(

    @PrimaryKey(autoGenerate = true) var id : Long?,

    @ColumnInfo(name="title") var title : String,

    @ColumnInfo(name = "body")var body : String)

    :Parcelable


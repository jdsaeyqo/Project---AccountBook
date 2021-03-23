package com.example.mymoneybook.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "moneybookTable")

data class Data(

    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "title")
    var date: String?,

    @ColumnInfo(name = "sep")
    var sep: String?,

    @ColumnInfo(name = "money")
    var money: String?,

    @ColumnInfo(name = "purp")
    var purp: String?,

    @ColumnInfo(name = "checked")
    var checked: String?

) : Parcelable






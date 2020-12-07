package com.example.mymoneybook

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moneybookTable")
class Data() :Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id : Long? = null

    @ColumnInfo(name="date")
    var date : String? = null
    @ColumnInfo(name="sep")
    var sep : String? = null
    @ColumnInfo(name="money")
    var money : String? = null
    @ColumnInfo(name="purp")
    var purp : String? = null
    @ColumnInfo(name="checked")
    var checked : String? = null

    constructor(parcel: Parcel) : this(){

        date = parcel.readString()
        sep = parcel.readString()
        money = parcel.readString()
        purp = parcel.readString()
        checked = parcel.readString()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(sep)
        parcel.writeString(money)
        parcel.writeString(purp)
        parcel.writeString(checked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}



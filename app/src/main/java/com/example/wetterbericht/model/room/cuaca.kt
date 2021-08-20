package com.example.wetterbericht.model.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tabelcuaca")
data class cuaca(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val loc : String,
    val desc : String,
    val temp : String,
    val uv : String,
    val humid : Int,
    val image : String
) : Parcelable
package com.example.wetterbericht.model.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tabelcuaca")
data class cuaca(
    @PrimaryKey(autoGenerate = false)
    val loc : String,
    val id : Int,
    val desc : String,
    val temp : String,
    val uv : String,
    val humid : Int,
    val image : String,
    val feelslike : String,
    val windspeed : String,
    val cloud : String,
    val visibility : String,
    val presure : String,

) : Parcelable
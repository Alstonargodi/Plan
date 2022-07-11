package com.example.wetterbericht.data.local.entity.weather

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "WeatherTable")
data class WeatherLocal(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val loc : String,
    val temp : String,
    val image : String,
    val feelLike : String,
    val humid : String,
    val desc : String
) : Parcelable
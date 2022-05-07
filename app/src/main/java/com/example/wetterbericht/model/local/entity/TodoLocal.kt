package com.example.wetterbericht.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "WeatherTable")
data class WeatherLocal(
    @PrimaryKey(autoGenerate = false)
    val loc : String,
    val temp : String,
    val image : String,
    val feelslike : String,
    val humid : String,
    val desc : String
) : Parcelable


@Parcelize
@Entity(tableName = "TodoTable")
data class TodoLocal(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val description: String,
    val level : String,
    val dateDeadline: String,
    val timeDeadline : String,
    val status: String,
):Parcelable

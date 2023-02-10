package com.example.wetterbericht.data.local.entities.dailyhabits

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "HabitsColor")
data class ColorHabits(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id : Int,
    @NonNull
    val colorHex : String,
):Parcelable

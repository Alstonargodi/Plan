package com.example.wetterbericht.data.local.entity.dailyhabits

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "HabitsIcon")
data class IconHabits(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id : Int,
    @NonNull
    val nameIcon : String,
    @NonNull
    val iconPath : String,
): Parcelable
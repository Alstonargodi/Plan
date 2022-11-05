package com.example.wetterbericht.data.local.entity.dailyhabits

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "HabitsTable")
data class DailyHabits(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val habitsId : Int,
    @NonNull
    val title : String,
    @NonNull
    val minuteFocus : Long,
    @NonNull
    val startTime : String,
    @NonNull
    val priorityLevel : String
): Parcelable
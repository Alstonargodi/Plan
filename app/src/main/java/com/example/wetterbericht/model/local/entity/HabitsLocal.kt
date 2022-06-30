package com.example.wetterbericht.model.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "HabitsTable")
data class HabitsLocal(
    @PrimaryKey(autoGenerate = true)
    val habitsId : Int,
    val name : String,
    val duration : Int,
): Parcelable
package com.example.wetterbericht.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ChipAlarmTable")
data class ChipAlarm(
    @PrimaryKey(autoGenerate = false)
    val name : String,
    val time : String
):Parcelable



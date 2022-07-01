package com.example.wetterbericht.model.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "HabitsTable")
data class HabitsLocal(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val habitsId : Int,
    @NonNull
    val name : String,
    @NonNull
    val duration : Int,
): Parcelable
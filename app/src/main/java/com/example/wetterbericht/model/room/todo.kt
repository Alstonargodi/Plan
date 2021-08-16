package com.example.wetterbericht.model.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "tabeltodo")
data class todo(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val desc : String,
    val deadline: String,
    val status : String,
): Parcelable
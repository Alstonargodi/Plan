package com.example.wetterbericht.model.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Date

@Parcelize
@Entity(tableName = "tabeltodo")
data class todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val desc: String,
    val deadlinedate: String,
    val deadlinetime : String,
    val status: String
): Parcelable
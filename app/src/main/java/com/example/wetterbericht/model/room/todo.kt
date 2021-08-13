package com.example.wetterbericht.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tabeltodo")
data class todo(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val desc : String,
    val deadline: String,
    val status : String,
)
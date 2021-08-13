package com.example.wetterbericht.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabelcuaca")
data class cuaca(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val loc : String,
    val desc : String,
    val temp : Int,
    val uv : Int,
    val humid : Int,
    val image : String
)
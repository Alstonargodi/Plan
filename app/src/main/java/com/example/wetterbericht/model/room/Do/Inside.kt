package com.example.wetterbericht.model.room

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.sql.Date

@Parcelize
@Entity(tableName = "tabelinside")
data class Inside(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val desc: String,
    val kat : String,
    val deadlinedate: String,
    val deadlinetime : String,
    val status: String,
):Parcelable

@Entity
data class subtaskinside(
    @PrimaryKey(autoGenerate = true)
    val idsub : Int,
    val task : String
)

//relationship
data class insidendsubtask(
    @Embedded
    val todo : Inside,
    @Relation(
        parentColumn = "title",
        entityColumn = "idsub"
    )
    val subtask : List<subtaskinside>
)

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
@Entity(tableName = "tabeltodo")
data class todo(
    @PrimaryKey(autoGenerate = false)
    val idtodo : Int,
    val title: String,
    val desc: String,
    val kat : String,
    val deadlinedate: String,
    val deadlinetime : String,
    val status: String,
):Parcelable

@Entity
data class subtask(
    @PrimaryKey(autoGenerate = false)
    val idsub : Int,
    val task : String
)

//relationship
data class todoandsubtask(
    @Embedded
    val todo : todo,
    @Relation(
        parentColumn = "idtodo",
        entityColumn = "idsub"
    )
    val subtask : List<subtask>
)

package com.example.wetterbericht.model.room.Do

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tabeloutside")
data class Outside(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val desc: String,
    val kat : String,
    val deadlinedate: String,
    val deadlinetime : String,
    val status: String,
): Parcelable

@Entity
data class subtaskoutside(
    @PrimaryKey(autoGenerate = true)
    val idsub : Int,
    val task : String
)

//relationship
data class outsideandsubtask(
    @Embedded
    val todo : Outside,
    @Relation(
        parentColumn = "title",
        entityColumn = "idsub"
    )
    val subtask : List<subtaskoutside>
)

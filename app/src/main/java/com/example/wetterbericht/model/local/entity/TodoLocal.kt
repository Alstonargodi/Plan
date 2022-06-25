package com.example.wetterbericht.model.local

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "TodoTable")
data class TodoLocal(
    @PrimaryKey
    val doId : String,
    val title: String,
    val description: String,
    val levelName : String,
    val levelColor : Int,
    val dateDeadline: String,
    val timeDeadline : String,
    val isComplete: Boolean,
):Parcelable


@Entity
data class TodoSubTask(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val isComplete: Boolean,
    val todoId : String
)

//one to many
data class TodoandSubTask(
    @Embedded
    val todo : TodoLocal,
    @Relation(
        parentColumn = "doId",
        entityColumn = "todoId"
    )
    val subtask : List<TodoSubTask>
)
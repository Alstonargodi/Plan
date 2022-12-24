package com.example.wetterbericht.data.local.entity.dailytask

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "TodoTable")
data class TodoLocal(
    @PrimaryKey(autoGenerate = true)
    val taskID : Int,
    val title: String,
    val description: String,
    val levelColor : Int,
    val dateStart: String,
    val dateDay : Int,
    val dateDueMillis : Long,
    val notificationInterval : Int,
    val startTime : String,
    val endTime : String,
    val subTaskId: String,
    @ColumnInfo(name = "completed")
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
        parentColumn = "subTaskId",
        entityColumn = "todoId"
    )
    val subtask : List<TodoSubTask>
)
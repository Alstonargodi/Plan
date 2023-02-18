package com.example.wetterbericht.data.local.entities.dailytask

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "TodoTable")
data class TodoLocal(
    @PrimaryKey(autoGenerate = true)
    val taskID : Int = 0,
    val title: String = "",
    val description: String = "",
    val levelColor : Int = 0,
    val dateDueMillis : Long = 0L,
    val notificationInterval : Int = 0,
    val dateStart: String = "",
    val dateYear : Int = 0,
    val dateMonth : Int = 0,
    val dateDay : Int = 0,
    val startTime : String = "",
    val endTime : String = "",
    val subTaskId: String = "",
    @ColumnInfo(name = "completed")
    val isComplete: Boolean = false,
    var isUploaded : Boolean = false
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
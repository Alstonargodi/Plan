package com.example.wetterbericht.data.local.dao.dailytask

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.*
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask

@Dao
abstract class DailyTaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertTodoList(data : TodoLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSubTask(sub : TodoSubTask)

    @RawQuery(observedEntities = [TodoLocal::class])
    abstract fun readTodoTaskFilter(query: SupportSQLiteQuery)
    : DataSource.Factory<Int,TodoLocal>

    @Query("select * from todotable where completed=0 order by dateDueMillis asc")
    abstract fun readNearestActiveTask(): TodoLocal

    @Query("select*from TodoTable")
    abstract fun readTodoList() : LiveData<List<TodoLocal>>

    @Query("select*from TodoTable where title = :name")
    abstract fun readSearchTodo(name: String): LiveData<List<TodoLocal>>

    @Transaction
    @Query("select * from todotable where taskID = :name")
    abstract fun readSubTaskTodoList(name : String): LiveData<List<TodoandSubTask>>

    @Query("update todosubtask set isComplete=:status where id=:taskid")
    abstract fun updateSubTaskStatus(taskid: Int,status : Boolean)

    @Query("update TodoTable set completed=:status where taskID=:id")
    abstract fun updateTaskStatus(id : Int,status : Boolean)

    @Query("delete from TodoTable where title like :name ")
    abstract fun deleteTodoTask(name : String)

    @Query("select * from todotable where dateDay =:date and completed =0 order by dateDueMillis asc")
    abstract fun readTodayTaskReminder(date: Int): List<TodoLocal>


    @Query("select * from todotable where dateDay = :date and dateMonth = :month and dateYear = :year")
    abstract fun readTodayTask(
        date: Int,
        month : Int,
        year : Int
    ):LiveData<List<TodoLocal>>

    @Query("select * from todotable where dateDay > :date and dateMonth >= :month and dateYear >= :year")
    abstract fun readUpcomingTask(
        date: Int,
        month : Int,
        year : Int
    ):LiveData<List<TodoLocal>>

    @Query("select * from todotable where dateDay < :date or dateMonth < :month or dateYear < :year")
    abstract fun readPreviousTask(
        date: Int,
        year : Int,
        month : Int
    ):LiveData<List<TodoLocal>>

    //time chip
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTimeChip(alarm : ChipAlarm)

    @Query("select*from ChipAlarmTable")
    abstract fun readTimeChip() : LiveData<List<ChipAlarm>>
}
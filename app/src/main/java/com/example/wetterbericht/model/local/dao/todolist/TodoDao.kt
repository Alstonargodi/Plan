package com.example.wetterbericht.model.local.dao.todolist

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wetterbericht.model.local.*
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.model.local.entity.todolist.TodoandSubTask

@Dao
abstract class TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTodo(data : TodoLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSubTask(sub : TodoSubTask)

    @Query("select*from TodoTable")
    abstract fun readTodo() : LiveData<List<TodoLocal>>


    @Query("select*from TodoTable where title = :name")
    abstract fun readSearchTodo(name: String): LiveData<List<TodoLocal>>

    @Transaction
    @Query("select * from TodoTable where title = :name")
    abstract fun getTodoSubtask(name : String): LiveData<List<TodoandSubTask>>


    @Query("select * from todotable where dateDay = :date")
    abstract fun getTodayTaskReminder(date: Int): List<TodoLocal>

    @Query("select * from todotable where dateDay = :date")
    abstract fun getTodayTask(date: Int):LiveData<List<TodoLocal>>

    @Query("select * from todotable where dateDay > :date")
    abstract fun getUpcomingTask(date: Int):LiveData<List<TodoLocal>>

    @Query("select * from todotable where dateDay < :date")
    abstract fun getPreviousTask(date: Int):LiveData<List<TodoLocal>>


    @Query("delete from TodoTable where title like :name ")
    abstract fun deleteTodo(name : String)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAlarmChip(alarm : ChipAlarm)

    @Query("select*from ChipAlarmTable")
    abstract fun readAlarmChip() : LiveData<List<ChipAlarm>>



}
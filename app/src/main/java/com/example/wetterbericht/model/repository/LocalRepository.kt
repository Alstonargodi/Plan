package com.example.wetterbericht.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.wetterbericht.model.local.*
import com.example.wetterbericht.model.local.dao.TodoDao
import com.example.wetterbericht.model.local.database.TodoDatabase
import com.example.wetterbericht.model.local.entity.WeatherLocal
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class LocalRepository(application: Application) {

    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()
    private val dao : TodoDao

    init {
        val db = TodoDatabase.setDatabase(application)
        dao = db.localDao()
    }

    val readTodo : LiveData<List<TodoLocal>> =
        dao.readTodo()
    val readWeather : LiveData<List<WeatherLocal>> =
        dao.readWeather()

    fun readTodo(name : String): LiveData<List<TodoLocal>> =
        dao.readSearchTodo(name)

    fun readTodoSubtask(name : String): LiveData<List<TodoandSubTask>> =
       dao.getTodoSubtask(name)

    fun insertWeather(data : WeatherLocal) =
        executorService.execute { dao.insertWeather(data) }

    fun insertTodo(data : TodoLocal) =
        executorService.execute{ dao.insertTodo(data) }

    fun insertSubtask(data : TodoSubTask){
        executorService.execute { dao.insertSubTask(data) }
    }

    fun searchLocation(name: String): LiveData<List<WeatherLocal>> =
        dao.searchLocation(name)

    fun deleteWeather(name : String) =
        dao.deleteWeather(name)
    fun deleteTodo(name : String) =
        dao.deleteTodo(name)


    val readChipAlarm : LiveData<List<ChipAlarm>> =
        dao.readAlarmChip()

    fun insertAlarmChip(alarm : ChipAlarm) =
        executorService.execute { dao.insertAlarmChip(alarm) }
}
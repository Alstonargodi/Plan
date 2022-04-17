package com.example.wetterbericht.model.local

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class LocalRepository(application: Application) {

    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()
    private val dao : LocalDao

    init {
        val db = LocalDatabase.setDatabase(application)
        dao = db.localDao()
    }

    val readTodo : LiveData<List<TodoLocal>> = dao.readTodo()
    val readWeather : LiveData<List<WeatherLocal>> = dao.readWeather()

    fun insertWeather(data : WeatherLocal) =
        executorService.execute { dao.insertWeather(data) }

    fun insertTodo(data : TodoLocal) =
        executorService.execute{ dao.insertTodo(data) }

    fun deleteWeather(name : String) = dao.deleteWeather(name)
    fun deleteTodo(name : String) = dao.deleteTodo(name)

}
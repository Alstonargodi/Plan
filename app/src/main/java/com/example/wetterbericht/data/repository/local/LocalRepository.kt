package com.example.wetterbericht.data.repository.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.wetterbericht.data.local.source.ILocalDataSource
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.entity.dailytask.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal

class LocalRepository(
    private val dataSource: ILocalDataSource
): ILocalRepository {

    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return dataSource.getOnBoardingStatus()
    }

    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
       dataSource.saveOnBoardingStatus(onBoard)
    }

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
        return dataSource.readChipTime()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        dataSource.insertChipTime(alarm)
    }

    override fun readNearestActiveTask(): TodoLocal {
        return dataSource.readNearestActiveTask()
    }

    override fun readTodoTaskFilter(query: SupportSQLiteQuery): DataSource.Factory<Int, TodoLocal> {
       return dataSource.readTodoTaskFilter(query)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return dataSource.readTodoLocal()
    }

    override fun readTodoSubtask(name: String): LiveData<List<TodoandSubTask>> {
        return dataSource.readTodoSubtask(name)
    }

    override fun getTodayTask(date : Int): LiveData<List<TodoLocal>> {
        return dataSource.getTodayTask(date)
    }

    override fun getUpComingTask(date: Int): LiveData<List<TodoLocal>> {
        return dataSource.getUpComingTask(date)
    }

    override fun getPreviousTask(date: Int): LiveData<List<TodoLocal>> {
        return dataSource.getPreviousTask(date)
    }

    override fun getTodayTaskReminder(date: Int): List<TodoLocal> {
        return dataSource.getTodayTaskReminder(date)
    }

    override fun insertTodoList(data: TodoLocal) {
        dataSource.insertTodoList(data)
    }

    override fun insertSubtask(data: TodoSubTask) {
        dataSource.insertSubtask(data)
    }

    override fun deleteTodoList(name: String) {
        dataSource.deleteTodoList(name)
    }

    override fun updateTaskStatus(id: Int, status: Boolean) {
        dataSource.updateTaskStatus(id, status)
    }

    override fun getHabits(query: SupportSQLiteQuery): DataSource.Factory<Int, DailyHabits> {
        return dataSource.getHabits(query)
    }

    override fun readHabitsLocal(): LiveData<List<DailyHabits>> {
        return dataSource.readHabitsLocal()
    }

    override fun insertHabitsLocal(data: DailyHabits) {
        dataSource.insertHabitsLocal(data)
    }

    override fun deleteHabitsLocal(name: String) {
        dataSource.deleteHabitsLocal(name)
    }

    override fun readWeatherLocal(): LiveData<List<WeatherLocal>> {
       return dataSource.readWeatherLocal()
    }

    override fun getWeatherCityName(): WeatherLocal {
        return dataSource.getWeatherByCityName()
    }

    override fun insertWeatherLocal(data: WeatherLocal) {
        dataSource.insertWeatherLocal(data)
    }

    override fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>> {
        return dataSource.searchWeatherLocal(name)
    }

}
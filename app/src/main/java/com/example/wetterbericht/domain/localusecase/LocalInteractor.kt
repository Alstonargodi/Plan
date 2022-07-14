package com.example.wetterbericht.domain.localusecase

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.data.local.entity.todolist.TodoandSubTask
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import com.example.wetterbericht.data.repository.local.ILocalRepository
import com.example.wetterbericht.helpers.sortfilter.HabitSortType
import com.example.wetterbericht.helpers.sortfilter.SortUtils
import java.time.LocalDateTime

//where business logic
class LocalInteractor(private val repository: ILocalRepository): LocalUseCase {
    private val currentDate = LocalDateTime.now().dayOfMonth

    override fun getOnBoardingStatus(): LiveData<Boolean> {
        return repository.getOnBoardingStatus()
    }

    override suspend fun saveOnBoardingStatus(onBoard: Boolean) {
        return repository.saveOnBoardingStatus(onBoard)
    }

    override fun readChipTime(): LiveData<List<ChipAlarm>> {
       return repository.readChipTime()
    }

    override fun insertChipTime(alarm: ChipAlarm) {
        return repository.insertChipTime(alarm)
    }

    override fun readTodoSubtask(name: String): LiveData<List<TodoandSubTask>> {
        return repository.readTodoSubtask(name)
    }

    override fun readTodoLocal(): LiveData<List<TodoLocal>> {
        return repository.readTodoLocal()
    }

    override fun getTodayTask(): LiveData<List<TodoLocal>> {
        return repository.getTodayTask(currentDate)
    }

    override fun getUpComingTask(): LiveData<List<TodoLocal>> {
        return repository.getUpComingTask(currentDate)
    }

    override fun getPreviousTask(): LiveData<List<TodoLocal>> {
        return repository.getPreviousTask(currentDate)
    }

    override fun getTodayTaskReminder(): List<TodoLocal> {
       return repository.getTodayTaskReminder(currentDate)
    }

    override fun insertTodoList(data: TodoLocal) {
        repository.insertTodoList(data)
    }

    override fun insertSubtask(data: TodoSubTask) {
        repository.insertSubtask(data)
    }

    override fun deleteTodoList(name: String) {
        repository.deleteTodoList(name)
    }

    override fun getHabits(filter : HabitSortType): LiveData<PagedList<HabitsLocal>> {
        val query = SortUtils.getSortedQuery(filter)
        val habits = repository.getHabits(query)

        val pagedConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(5)
            .build()

        return LivePagedListBuilder(habits,pagedConfig).build()
    }

    override fun readHabitsLocal(): LiveData<List<HabitsLocal>> {
        return repository.readHabitsLocal()
    }

    override fun insertHabitsLocal(data: HabitsLocal) {
        repository.insertHabitsLocal(data)
    }

    override fun deleteHabitsLocal(name: String) {
        repository.readHabitsLocal()
    }

    override fun readWeatherLocal(): LiveData<List<WeatherLocal>> {
        return repository.readWeatherLocal()
    }

    override fun getWeatherCityname(): WeatherLocal {
        return repository.getWeatherCityName()
    }

    override fun insertWeatherLocal(data: WeatherLocal) {
        repository.insertWeatherLocal(data)
    }

    override fun searchWeatherLocal(name: String): LiveData<List<WeatherLocal>> {
       return repository.searchWeatherLocal(name)
    }
}
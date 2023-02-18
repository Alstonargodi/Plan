package com.example.wetterbericht.presentation.fragment.insertnewtask.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entities.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entities.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.preferences.UserProfile
import com.example.wetterbericht.domain.localusecase.datauser.ProfileUseCase
import com.example.wetterbericht.domain.localusecase.habits.HabitsLocalUseCase
import com.example.wetterbericht.domain.localusecase.todotask.TodoLocalUseCase
import com.example.wetterbericht.domain.remoteusecase.firebase.realtimedb.TodoRemoteUseCase
import com.example.wetterbericht.helpers.sortfilter.TodoSortType
import com.google.firebase.database.DatabaseReference

class InsertTodoViewModel(
    private val todoLocal: TodoLocalUseCase,
    private val colorUseCase : HabitsLocalUseCase,
    private val todoRemote : TodoRemoteUseCase,
    private val profileUseCase: ProfileUseCase
): ViewModel() {
    private val todoFilter = MutableLiveData<TodoSortType>()

    init {
        todoFilter.value = TodoSortType.ALL_TASKS
    }

    fun readTodoTaskFilter(): LiveData<PagedList<TodoLocal>> =
        todoFilter.switchMap {
            todoLocal.readTodoTaskFilter(it)
        }

    fun readColorList(): LiveData<List<ColorHabits>> =
        colorUseCase.getHabitsColor()

    fun readAlarmChip(): LiveData<List<ChipAlarm>> =
        todoLocal.readChipTime()

    fun insertTodoLocal(data : TodoLocal) =
        todoLocal.insertTodoList(data)

    fun insertAlarmChip(alarm : ChipAlarm) =
        todoLocal.insertChipTime(alarm)

    fun insertSubtask(data : TodoSubTask) =
        todoLocal.insertSubtask(data)

    fun insertTodoRemote(data : TodoLocal,userId : String) =
        todoRemote.insertTodolistRemote(data,userId)

    fun readUserFirebaseId(): LiveData<UserProfile> =
        profileUseCase.getProfilePreferences().asLiveData()
}
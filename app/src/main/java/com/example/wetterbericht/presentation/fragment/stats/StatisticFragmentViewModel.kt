package com.example.wetterbericht.presentation.fragment.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.domain.localusecase.todotask.TodoLocalUseCase

class StatisticFragmentViewModel(
    private val todoLocalUseCase: TodoLocalUseCase
): ViewModel() {

    fun readTodoTask(): LiveData<List<TodoLocal>>{
        return todoLocalUseCase.readTodoLocal()
    }

}
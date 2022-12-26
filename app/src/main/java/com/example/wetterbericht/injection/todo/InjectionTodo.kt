package com.example.wetterbericht.injection.todo

import android.content.Context
import com.example.wetterbericht.data.local.source.todotask.ITodoLocalDataSource
import com.example.wetterbericht.data.local.source.todotask.TodoLocalDataSource
import com.example.wetterbericht.data.repository.local.todo.ITodoLocalRepository
import com.example.wetterbericht.data.repository.local.todo.TodoLocalRepository
import com.example.wetterbericht.domain.localusecase.todotask.TodoLocalInteractor
import com.example.wetterbericht.domain.localusecase.todotask.TodoLocalUseCase

object InjectionTodo {
    fun provideTodoUseCase(context: Context): TodoLocalUseCase{
        return TodoLocalInteractor(provideTodoRespository(context))
    }
    private fun provideTodoRespository(context: Context)
    : ITodoLocalRepository{
        return TodoLocalRepository(provideTodoDataSource(context))
    }
    private fun provideTodoDataSource(context: Context)
    : ITodoLocalDataSource{
        return TodoLocalDataSource(context)
    }
}
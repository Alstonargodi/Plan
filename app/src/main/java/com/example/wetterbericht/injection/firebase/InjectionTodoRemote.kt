package com.example.wetterbericht.injection.firebase

import com.example.wetterbericht.data.remote.firebase.realtimedb.TodoRemoteService
import com.example.wetterbericht.data.repository.remote.firebase.realtimedb.TodoRemoteRepository
import com.example.wetterbericht.domain.remoteusecase.firebase.realtimedb.TodoRemoteUseCase

object InjectionTodoRemote {
    fun provideTodoRemoteUseCase(): TodoRemoteUseCase{
        return TodoRemoteUseCase(
            provideTodoRemoteReposiotry()
        )
    }
    fun provideTodoRemoteReposiotry(): TodoRemoteRepository{
        return TodoRemoteRepository(TodoRemoteService())
    }
}

package com.example.wetterbericht.domain.remoteusecase.firebase.realtimedb

import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.example.wetterbericht.data.repository.remote.firebase.realtimedb.TodoRemoteRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

class TodoRemoteUseCase(
    private val todo: TodoRemoteRepository
): ITodoRemoteUseCase {
    override fun insertTodolistRemote(data: TodoLocal, userId: String): Task<Void> {
        return todo.insertTodolistRemote(data, userId)
    }
    override fun readTodolistRemote(userId: String): DatabaseReference {
        return todo.readTodolistRemote(userId)
    }

}
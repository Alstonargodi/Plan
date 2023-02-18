package com.example.wetterbericht.data.repository.remote.firebase.realtimedb

import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.example.wetterbericht.data.remote.firebase.realtimedb.TodoRemoteService
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

class TodoRemoteRepository(
    private val todo: TodoRemoteService
): ITodoRemoteRepository {
    override fun insertTodolistRemote(data: TodoLocal, userId: String): Task<Void> {
        return todo.insertTodolistRemote(data, userId)
    }

    override fun readTodolistRemote(userId: String): DatabaseReference {
        return todo.readTodolistRemote(userId)
    }

}
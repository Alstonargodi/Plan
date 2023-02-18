package com.example.wetterbericht.data.repository.remote.firebase.realtimedb

import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

interface ITodoRemoteRepository {
    fun insertTodolistRemote(data : TodoLocal, userId : String): Task<Void>
    fun readTodolistRemote(userId: String): DatabaseReference
}
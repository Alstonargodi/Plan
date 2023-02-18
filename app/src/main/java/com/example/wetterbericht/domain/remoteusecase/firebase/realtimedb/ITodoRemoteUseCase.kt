package com.example.wetterbericht.domain.remoteusecase.firebase.realtimedb

import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

interface ITodoRemoteUseCase {
    fun insertTodolistRemote(data : TodoLocal, userId : String): Task<Void>
    fun readTodolistRemote(userId: String): DatabaseReference
}
package com.example.wetterbericht.data.remote.firebase.realtimedb

import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.google.android.gms.tasks.Task

interface ITodoRemoteService {
    fun insertTodolistRemote(data : TodoLocal,userId : String): Task<Void>
}
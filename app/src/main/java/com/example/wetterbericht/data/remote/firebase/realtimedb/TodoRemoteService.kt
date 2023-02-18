package com.example.wetterbericht.data.remote.firebase.realtimedb

import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TodoRemoteService: ITodoRemoteService {
    override fun insertTodolistRemote(data: TodoLocal, userId: String): Task<Void> =
       FirebaseDatabase.getInstance()
            .getReference(mainPath)
            .child(userId)
            .child(todoPath)
            .child(data.dateStart)
            .child(data.subTaskId)
            .setValue(data)

    override fun readTodolistRemote(userId: String): DatabaseReference =
        FirebaseDatabase.getInstance()
            .getReference(mainPath)
            .child(userId)
            .child(todoPath)


    companion object{
        const val mainPath = "MainUserData"
        const val todoPath = "Todolist"
    }
}
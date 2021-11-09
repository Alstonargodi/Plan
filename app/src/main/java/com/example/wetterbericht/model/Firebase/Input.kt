package com.example.wetterbericht.model.Firebase

import android.util.Log
import com.example.wetterbericht.model.room.subtaskinside
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Input {
    lateinit var databaseReference : DatabaseReference

    fun inputinside(data : Insidedo,subtaskdo: List<subtaskinside>){
        databaseReference = FirebaseDatabase.getInstance("https://wetterbericht-3f094-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("data")
        val userid = FirebaseAuth.getInstance().currentUser?.uid.toString()


        for (i in 0 until subtaskdo.size){
            val datasub = Subtaskdo(
                subtaskdo[i].idsub,
                subtaskdo[i].task,
            )

            Log.d("listtask", datasub.toString())

            databaseReference.child("a").child("inside").child("a").setValue(data)
            databaseReference.child("a").child("inside").child("a").child("subtask").child(subtaskdo[i].idsub).setValue(subtaskdo[i].task)

        }
    }
}
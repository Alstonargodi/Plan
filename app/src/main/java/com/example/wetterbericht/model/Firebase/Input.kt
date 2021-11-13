package com.example.wetterbericht.model.Firebase

import android.util.Log
import android.widget.Toast
import com.example.wetterbericht.model.room.Do.Outside
import com.example.wetterbericht.model.room.Do.subtaskoutside
import com.example.wetterbericht.model.room.Inside
import com.example.wetterbericht.model.room.subtaskinside
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Input {

    val databaseReference = FirebaseDatabase.getInstance("https://wetterbericht-3f094-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("data")
    val userid = FirebaseAuth.getInstance().currentUser?.uid.toString()

    lateinit var insidelist : ArrayList<Insidedo>
    lateinit var subinsidelist : ArrayList<Subtaskdo>

    fun inputinside(data : Inside,subtaskdo: List<subtaskinside>){
        val userid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val judul = data.title


        for (i in 0 until subtaskdo.size){
            val datasub = Subtaskdo(
                subtaskdo[i].idsub,
                subtaskdo[i].task,
            )

            Log.d("listtask", datasub.toString())

            databaseReference.child(userid).child("inside").child(judul).setValue(data)
            databaseReference.child(userid).child("inside").child(judul).child("subtask").child(judul).setValue(subtaskdo)
        }
    }

    fun inputoutside(data : Outside,subtaskoutside: List<subtaskoutside>){

    }

    fun retrivedataall(){
        insidelist = arrayListOf()
        subinsidelist = arrayListOf()

        //main inside
        databaseReference.child(userid).child("inside").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(item in snapshot.children){
                        val data= item.getValue(Insidedo::class.java)
                        insidelist.add(data!!)

                        val insidedata = Insidedo(
                            data.title,
                            data.desc,
                            data.kat,
                            data.deadlinedate,
                            data.deadlinetime,
                            data.status
                        )
                        Log.d("tes",insidedata.toString())

                        val judul = data.title.toString()
                        //sub

                        //todo alarm manager notif
                        databaseReference.child(userid).child("inside").child(judul).child("subtask").child(judul).addValueEventListener(object : ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()){
                                    for(subitem in snapshot.children){


                                        val data = subitem.getValue(Subtaskdo::class.java)
                                        subinsidelist.add(data!!)

                                        val subdata = Subtaskdo(
                                            data.idsub,
                                            data.task
                                        )

                                        Log.d("subtask",subdata.toString())

                                    }
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        })
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("error",error.toString())
            }
        })



    }
}
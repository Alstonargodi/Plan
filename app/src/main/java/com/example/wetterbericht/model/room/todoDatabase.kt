package com.example.wetterbericht.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wetterbericht.model.dao.todoDao

@Database(entities = [todo::class],version = 1,exportSchema = false)
abstract class todoDatabase: RoomDatabase() {
    abstract fun todoDao() : todoDao

    companion object{
        @Volatile
        private var minstance : todoDatabase? = null

        fun setdatabase(context: Context): todoDatabase{
            val tempinstace = minstance
            if(tempinstace != null){
                return tempinstace
            }else{
                synchronized(this){
                    val instance = Room.databaseBuilder(context.applicationContext,
                        todoDatabase::class.java,"tododatabase").build()
                    minstance = instance
                    return instance
                }
            }
        }
    }

}

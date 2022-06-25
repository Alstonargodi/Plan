package com.example.wetterbericht.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.TodoSubTask
import com.example.wetterbericht.model.local.dao.TodoDao
import com.example.wetterbericht.model.local.entity.WeatherLocal

@Database(entities = [
    WeatherLocal::class,
    TodoLocal::class,
    TodoSubTask::class,
    ChipAlarm::class
    ]
    , version = 12
    ,exportSchema = false
)
abstract class TodoDatabase: RoomDatabase() {
   abstract fun localDao() : TodoDao

   companion object{
       @Volatile
       private var minstance : TodoDatabase? = null

       fun setDatabase(context: Context): TodoDatabase {
           val INSTANCE = minstance
           if(INSTANCE != null){
               return INSTANCE
           }else{
               synchronized(this){
                val instance = Room
                    .databaseBuilder(context.applicationContext, TodoDatabase::class.java,"dbcuac")
                    .fallbackToDestructiveMigration()
                    .build()
                   minstance = instance
                   return instance
               }
           }
       }
   }
}
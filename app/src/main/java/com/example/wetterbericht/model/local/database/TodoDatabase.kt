package com.example.wetterbericht.model.local.database

import android.content.Context
import androidx.room.*
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.TodoSubTask
import com.example.wetterbericht.model.local.WeatherLocal
import com.example.wetterbericht.model.local.dao.TodoDao

@Database(entities = [
    WeatherLocal::class,
    TodoLocal::class,
    TodoSubTask::class,
    ChipAlarm::class
    ]
    , version = 11
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
package com.example.wetterbericht.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.todolist.TodoLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.data.local.dao.habits.HabitsDao
import com.example.wetterbericht.data.local.dao.todolist.TodoDao
import com.example.wetterbericht.data.local.dao.weather.WeatherDao
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal

@Database(entities = [
    WeatherLocal::class,
    TodoLocal::class,
    TodoSubTask::class,
    ChipAlarm::class,
    HabitsLocal::class
    ]
    ,version = 17
    ,exportSchema = false
)
abstract class LocalDatabase: RoomDatabase() {
   abstract fun todoDao() : TodoDao
   abstract fun weatherDao(): WeatherDao
   abstract fun habitsDao(): HabitsDao

   companion object{
       @Volatile
       private var mInstance : LocalDatabase? = null

       fun setDatabase(context: Context): LocalDatabase {
           val INSTANCE = mInstance
           if(INSTANCE != null){
               return INSTANCE
           }else{
               synchronized(this){
                val instance = Room
                    .databaseBuilder(context.applicationContext, LocalDatabase::class.java,"dbcuac")
                    .fallbackToDestructiveMigration()
                    .build()
                   mInstance = instance
                   return instance
               }
           }
       }
   }
}
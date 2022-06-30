package com.example.wetterbericht.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.TodoSubTask
import com.example.wetterbericht.model.local.dao.HabitsDao
import com.example.wetterbericht.model.local.dao.TodoDao
import com.example.wetterbericht.model.local.dao.WeatherDao
import com.example.wetterbericht.model.local.entity.HabitsLocal
import com.example.wetterbericht.model.local.entity.WeatherLocal

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
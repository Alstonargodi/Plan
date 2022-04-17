package com.example.wetterbericht.model.local

import android.content.Context
import androidx.room.*

@Database(entities = [
    WeatherLocal::class,
    TodoLocal::class
    ]
    , version = 3
    ,exportSchema = false
)
abstract class LocalDatabase: RoomDatabase() {

   abstract fun localDao() : LocalDao

   companion object{
       @Volatile
       private var minstance : LocalDatabase? = null

       fun setDatabase(context: Context): LocalDatabase {
           val INSTANCE = minstance
           if(INSTANCE != null){
               return INSTANCE
           }else{
               synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                        LocalDatabase::class.java,"dbcuac"
                ).fallbackToDestructiveMigration().build()
                   minstance = instance
                   return instance
               }
           }
       }
   }
}
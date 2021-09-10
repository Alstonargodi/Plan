package com.example.wetterbericht.model.room

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.wetterbericht.model.dao.cuacaDao
import java.lang.Exception

@Database(entities = [cuaca::class], version = 1,exportSchema = false)
abstract class cuacaDatabase: RoomDatabase() {
   abstract fun cuacaDao() : cuacaDao

   companion object{
       @Volatile
       private var minstance : cuacaDatabase? = null

       fun setdatabase(context: Context): cuacaDatabase{
           val tempinstace = minstance
           if(tempinstace != null){
               return tempinstace
           }else{
               synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                        cuacaDatabase::class.java,"weatherdata").build()
                   minstance = instance
                   return instance
               }
           }
       }
   }
}
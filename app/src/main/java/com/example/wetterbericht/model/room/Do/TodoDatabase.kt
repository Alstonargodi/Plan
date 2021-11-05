package com.example.wetterbericht.model.room.Do

import android.content.Context
import androidx.room.*
import com.example.wetterbericht.model.dao.todoDao
import com.example.wetterbericht.model.room.Inside
import com.example.wetterbericht.model.room.subtaskinside
import com.example.wetterbericht.model.util.converters

@Database(entities = [
    Inside::class,
    Outside::class,
    subtaskoutside::class,
    subtaskinside::class],
    version = 11,
    exportSchema = false)
@TypeConverters(converters::class)
abstract class todoDatabase: RoomDatabase() {
    abstract fun todoDao() : todoDao

    companion object{
        @Volatile
        private var minstance : todoDatabase? = null

        fun setdatabase(context: Context): todoDatabase {
            val tempinstace = minstance
            if(tempinstace != null){
                return tempinstace
            }else{
                synchronized(this){
                    val instance = Room.databaseBuilder(context.applicationContext,
                        todoDatabase::class.java,"todat").fallbackToDestructiveMigration().build()
                    minstance = instance
                    return instance
                }
            }
        }
    }

}

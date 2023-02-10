package com.example.wetterbericht.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entities.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entities.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.dao.dailyhabits.DailyHabitsDao
import com.example.wetterbericht.data.local.dao.dailytask.DailyTaskDao
import com.example.wetterbericht.data.local.dao.weather.WeatherDao
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadAlarmChip
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadColoHabitsJson
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadHabitsJson
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadIconHabits
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadSubTaskJson
import com.example.wetterbericht.data.local.entities.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.IconHabits
import com.example.wetterbericht.data.local.entities.weather.WeatherLocal
import org.json.JSONException
import java.util.concurrent.Executors

@Database(entities = [
        WeatherLocal::class,
        TodoLocal::class,
        TodoSubTask::class,
        ChipAlarm::class,
        DailyHabits::class,
        IconHabits::class,
        ColorHabits::class
    ]
    ,version = 1
    ,exportSchema = false
)
abstract class RoomDatabaseConfig: RoomDatabase() {
   abstract fun todoDao() : DailyTaskDao
   abstract fun weatherDao(): WeatherDao
   abstract fun habitsDao(): DailyHabitsDao


   companion object{
       @Volatile
       private var mInstance : RoomDatabaseConfig? = null

       fun setInstance(context: Context): RoomDatabaseConfig {
            return mInstance ?: synchronized(this){
                val dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDatabaseConfig::class.java,
                        "planDB"
                    ).addCallback(object : Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            //TODO 3
                            Executors.newSingleThreadExecutor().execute {
                                fillWithStartingData(
                                    context,
                                    setInstance(context).habitsDao(),
                                    setInstance(context).todoDao()
                                )
                            }
                        }
                    }).build()
                   mInstance = dbInstance
                   dbInstance
               }
           }

       /*
            populate task database
        */
       private fun fillWithStartingData(
           context: Context,
           dailyHabitsDao: DailyHabitsDao,
           dailyTaskDao: DailyTaskDao,
       ){
           val habitsJsonArray = loadHabitsJson(context)
           val subtaskJsonArray = loadSubTaskJson(context)
           val iconHabitsArray = loadIconHabits(context)
           val chipAlarmArray = loadAlarmChip(context)
           val colorHabitsArray = loadColoHabitsJson(context)

           try {
               if (habitsJsonArray != null){
                   for (i in 0 until habitsJsonArray.length()){
                       val item = habitsJsonArray.getJSONObject(i)
                       dailyHabitsDao.insertHabits(DailyHabits(
                           habitsId = item.getInt("id"),
                           title = item.getString("title"),
                           minuteFocus = item.getLong("focusTime"),
                           startTime = item.getString("startTime"),
                            "",
                           -1
                       ))
                   }
               }

               if(subtaskJsonArray != null){
                   for (i in 0 until subtaskJsonArray.length()){
                       val item = subtaskJsonArray.getJSONObject(i)
                       dailyTaskDao.insertSubTask(
                           TodoSubTask(
                               id = 0,
                               title = item.getString("title"),
                               isComplete = item.getBoolean("isComplete"),
                               todoId = item.getInt("todoId").toString()
                           )
                       )
                   }
               }

               if (iconHabitsArray != null){
                   for (i in 0 until iconHabitsArray.length()){
                       val item = iconHabitsArray.getJSONObject(i)
                       dailyHabitsDao.insertHabitsIcon(
                           IconHabits(
                               id = 0,
                               nameIcon = item.getString("nameIcon"),
                               iconPath = item.getString("iconPath"),
                           )
                       )
                   }
               }

               if(chipAlarmArray != null){
                   for (i in 0 until chipAlarmArray.length()){
                       val item = chipAlarmArray.getJSONObject(i)
                       dailyTaskDao.insertTimeChip(
                           ChipAlarm(
                               name = item.getString("name"),
                               time = item.getString("time"),
                               sumDay = item.getInt("sumday")
                           )
                       )
                   }
               }

               if (colorHabitsArray != null){
                   for (i in 0 until colorHabitsArray.length()){
                       val items = colorHabitsArray.getJSONObject(i)
                       dailyHabitsDao.insertColorHabits(
                           ColorHabits(
                               id = 0,
                               colorHex = items.getString("colorHex")
                           )
                       )
                   }
               }

           }catch (e : JSONException){
               e.printStackTrace()
           }
       }
   }
}
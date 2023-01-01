package com.example.wetterbericht.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wetterbericht.R
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.data.local.dao.dailyhabits.DailyHabitsDao
import com.example.wetterbericht.data.local.dao.dailytask.DailyTaskDao
import com.example.wetterbericht.data.local.dao.weather.WeatherDao
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadAlarmChip
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadColoHabitsJson
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadHabitsJson
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadIconHabits
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadSubTaskJson
import com.example.wetterbericht.data.local.database.LoadDataFromJson.loadTodoJson
import com.example.wetterbericht.data.local.entity.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.IconHabits
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.time.LocalDateTime
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
abstract class LocalDatabase: RoomDatabase() {
   abstract fun todoDao() : DailyTaskDao
   abstract fun weatherDao(): WeatherDao
   abstract fun habitsDao(): DailyHabitsDao


   companion object{
       @Volatile
       private var mInstance : LocalDatabase? = null

       fun setInstance(context: Context): LocalDatabase {
            return mInstance ?: synchronized(this){
                val dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
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
            populate task database for testing purpose
        */
       private fun fillWithStartingData(
           context: Context,
           dailyHabitsDao: DailyHabitsDao,
           dailyTaskDao: DailyTaskDao,
       ){
           val habitsJsonArray = loadHabitsJson(context)
           val todoJsonArray = loadTodoJson(context)
           val subtaskJsonArray = loadSubTaskJson(context)
           val iconHabitsArray = loadIconHabits(context)
           val chipAlarmArray = loadAlarmChip(context)
           val colorHabitsArray = loadColoHabitsJson(context)
           val currentDate = LocalDateTime.now().dayOfMonth

           //pre popualte data
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

               if (todoJsonArray != null){
                   for (i in 0 until todoJsonArray.length()){
                       val item = todoJsonArray.getJSONObject(i)
                       dailyTaskDao.insertTodoList(TodoLocal(
                           taskID = item.getInt("id"),
                           title = item.getString("title"),
                           description = item.getString("description"),
                           levelColor = item.getInt("levelColor") ,
                           dateStart = item.getString("dateStart"),
                           dateDay = currentDate,
                           dateDueMillis = item.getLong("dueDate") ,
                           notificationInterval = item.getInt("notificationInterval"),
                           startTime = item.getString("startTime"),
                           endTime = item.getString("endTime") ,
                           isComplete = item.getBoolean("completed"),
                           subTaskId = "a"
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
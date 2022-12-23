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
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
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
        DailyHabits::class
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
           val currentDate = LocalDateTime.now().dayOfMonth

           try {

               if (habitsJsonArray != null){
                   for (i in 0 until habitsJsonArray.length()){
                       val item = habitsJsonArray.getJSONObject(i)
                       dailyHabitsDao.insertHabits(DailyHabits(
                           habitsId = item.getInt("id"),
                           title = item.getString("title"),
                           minuteFocus = item.getLong("focusTime"),
                           startTime = item.getString("startTime"),
                           priorityLevel = item.getString("priorityLevel")
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
                           isComplete = item.getBoolean("completed")
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

           }catch (e : JSONException){
               e.printStackTrace()
           }
       }

       private fun loadHabitsJson(context: Context): JSONArray?{
           val builder = StringBuilder()
           val resources = context.resources.openRawResource(R.raw.habit)
           val reader = BufferedReader(InputStreamReader(resources))
           var line : String?
           try {
                while (reader.readLine().also { line = it } != null){
                    builder.append(line)
                }
               val json = JSONObject(builder.toString())
               return json.getJSONArray("habits")
           }catch (exception: IOException) {
               exception.printStackTrace()
           } catch (exception: JSONException) {
               exception.printStackTrace()
           }
           return null
       }

       private fun loadTodoJson(context: Context): JSONArray?{
           val builder = StringBuilder()
           val resources = context.resources.openRawResource(R.raw.task)
           val reader = BufferedReader(InputStreamReader(resources))
           var line : String?
           try {
               while (reader.readLine().also { line = it } != null){
                   builder.append(line)
               }
               val json = JSONObject(builder.toString())
               return json.getJSONArray("tasks")
           }catch (e : IOException){
               e.printStackTrace()
           }catch (e : JSONException){
               e.printStackTrace()
           }
           return null
       }

       private fun loadSubTaskJson(context: Context): JSONArray?{
           val builder = StringBuilder()
           val resources = context.resources.openRawResource(R.raw.task)
           val reader = BufferedReader(InputStreamReader(resources))
           var line : String?
           try {
               while (reader.readLine().also { line = it } != null){
                   builder.append(line)
               }
               val json = JSONObject(builder.toString())
               return json.getJSONArray("subtask")
           }catch (e : IOException){
               e.printStackTrace()
           }catch (e : JSONException){
               e.printStackTrace()
           }
           return null
       }
   }
}
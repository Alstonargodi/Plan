package com.example.wetterbericht.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wetterbericht.R
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.data.local.entity.todolist.TodoLocal
import com.example.wetterbericht.data.local.entity.todolist.TodoSubTask
import com.example.wetterbericht.data.local.dao.habits.HabitsDao
import com.example.wetterbericht.data.local.dao.todolist.TodoDao
import com.example.wetterbericht.data.local.dao.weather.WeatherDao
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.Executors

@Database(entities = [
        WeatherLocal::class,
        TodoLocal::class,
        TodoSubTask::class,
        ChipAlarm::class,
        HabitsLocal::class
    ]
    ,version = 1
    ,exportSchema = false
)
abstract class LocalDatabase: RoomDatabase() {
   abstract fun todoDao() : TodoDao
   abstract fun weatherDao(): WeatherDao
   abstract fun habitsDao(): HabitsDao

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


       private fun fillWithStartingData(
           context: Context, habitsDao: HabitsDao, todoDao: TodoDao
       ){
           val habitsJsonArray = loadHabitsJson(context)
           val todoJsonArray = loadTodoJson(context)
           try {
               if (habitsJsonArray != null){
                   for (i in 0 until habitsJsonArray.length()){
                       val item = habitsJsonArray.getJSONObject(i)
                       habitsDao.insertHabits(HabitsLocal(
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
                       todoDao.insertTodoList(TodoLocal(
                           taskID = item.getInt("id").toString(),
                           title = item.getString("title"),
                           description = item.getString("description"),
                           levelColor = item.getInt("levelColor") ,
                           dateStart = item.getString("dateStart"),
                           dateDay = item.getInt("dateDay"),
                           dateDueMillis = item.getLong("dueDate") ,
                           notificationInterval = item.getInt("notificationInterval"),
                           startTime = item.getString("startTime"),
                           endTime = item.getString("endTime") ,
                           isComplete = item.getBoolean("completed")
                       ))
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
   }
}
package com.example.wetterbericht.util

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.wetterbericht.view.MainActivity
import com.example.wetterbericht.R
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.database.LocalDatabase
import com.example.wetterbericht.model.repository.LocalRepository
import java.util.*
import java.util.concurrent.Executors

class TaskReminder : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Executors.newSingleThreadExecutor().execute {
            val task = LocalRepository(LocalDatabase.setDatabase(context)).getTodayTaskReminder()
            showAlarmNotification(context,task)
        }
    }


    fun setDailyReminder(context: Context,interval : Long){
        val alarManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context,TaskReminder::class.java)

        //start reminder from 5 am
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 5)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            ID_REPEATING,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            interval,
            pendingIntent
        )

    }


    private fun showAlarmNotification(context: Context, data : List<TodoLocal>){
        data.forEach {
            val notificationStyle = NotificationCompat.InboxStyle()
            val notificationFormat = context.resources.getString(R.string.notification_format)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val intent = Intent(context, MainActivity::class.java)

            val pendingIntent = PendingIntent.getActivity(
                context,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            data.forEach { value ->
                val task = String.format(notificationFormat,value.startTime,value.endTime,value.title)
                notificationStyle.addLine(task)
            }


            val builder = NotificationCompat.Builder(context, NOTIFICATION_Channel_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_task)
                .setContentTitle("Today task")
                .setStyle(notificationStyle)
                .setColor(ContextCompat.getColor(context,android.R.color.transparent))
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel = NotificationChannel(
                    NOTIFICATION_Channel_ID,
                    NOTIFICATION_Channel_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )

                channel.enableVibration(true)
                channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
                builder.setChannelId(NOTIFICATION_Channel_ID)
                notificationManager.createNotificationChannel(channel)
            }

            val notification = builder.build()
            notificationManager.notify(NOTIFICATION_ID,notification)
        }
    }

    fun cancelAlarm(context : Context){
        val alarManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context,TaskReminder::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            ID_REPEATING,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        pendingIntent.cancel()
        alarManager.cancel(pendingIntent)
    }


    companion object{
        const val ID_REPEATING = 101
        const val NOTIFICATION_ID = 201
        const val NOTIFICATION_Channel_ID = "Repeat_Notification"
        const val NOTIFICATION_Channel_NAME = "Repeat_task"
    }
}
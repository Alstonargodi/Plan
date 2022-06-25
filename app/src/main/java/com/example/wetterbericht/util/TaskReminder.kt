package com.example.wetterbericht.util

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.wetterbericht.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TaskReminder : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(extra_type)
        val message = intent.getStringExtra(extra_message)
        val id = intent.getIntExtra(extra_id,0)

        if (message != null && type !=null){
            showAlarmNotification(context,type,message,id)
        }
    }

    fun setOneAlarm(
        context: Context,
        type : String,
        date : String,
        time : String,
        message : String,
        id : Int
    ){
        if (isDateInvalid(date, date_format) || isDateInvalid(time, time_format))
            return

        val alarManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, TaskReminder::class.java)
        intent.putExtra(extra_message,message)
        intent.putExtra(extra_type,type)
        intent.putExtra(extra_id,id)


        Log.d("set time to","$date $time")
        val dateArray = date.split("-").toTypedArray()
        val timeArray = time.split(":").toTypedArray()


        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR,Integer.parseInt(dateArray[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]) - 1)
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[2]))
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            id,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )

    }


    private fun showAlarmNotification(
        context: Context,
        title : String,
        message : String,
        notificationId : Int
    ){
        val channelId = "channel_1"
        val channelName = "Alarm_channel"

        val notificationManagerCompact = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context,channelId)
            .setSmallIcon(R.drawable.ic_baseline_star_24)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context,android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

        //todo 1.5 add notif channel FOR ANDROID OREO +
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(channelId)
            notificationManagerCompact.createNotificationChannel(channel)
        }


        val notification = builder.build()
        Log.d("alarm","alarm muni")
        notificationManagerCompact.notify(notificationId,notification)
    }


    private fun isDateInvalid(date : String,format : String): Boolean{
        return try {
            val dateFormat = SimpleDateFormat(format, Locale.getDefault())
            dateFormat.isLenient = false
            dateFormat.parse(date)
            Log.d("alarm receiver","invalid")
            false
        }catch (e : ParseException){
            Log.d("alarm receiver","valid")
            true
        }
    }


    companion object{
        const val date_format = "yyyy-MM-dd"
        const val time_format = "HH:mm"

        const val extra_type = "type"
        const val extra_message = "pesan"
        const val extra_id = "id"

        const val type_one_time = "onetimealarm"
//        const val ONETIME_ID = 100
    }
}
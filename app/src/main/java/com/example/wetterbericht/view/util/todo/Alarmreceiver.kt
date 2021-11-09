package com.example.wetterbericht.view.util.todo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.wetterbericht.MainActivity
import com.example.wetterbericht.R

class Alarmreceiver: BroadcastReceiver() {
    //notif
    val name = "todo alarm"
    val desc = "test"
    val idnotif = "alarmid"
    val notifid = 0
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            notif(context)
        }

    }

    fun notif(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Log.d("alarm", "alarm")

            val notification = NotificationCompat.Builder(context)
                .setContentTitle("test notif")
                .setContentText("notifikasi")
                .setSmallIcon(R.drawable.ic_star_black_24dp)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build()
            val notificationmanager = NotificationManagerCompat.from(context)

            notificationmanager.notify(notifid,notification)

            val important = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(idnotif, name, important).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            channel.description = desc

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}
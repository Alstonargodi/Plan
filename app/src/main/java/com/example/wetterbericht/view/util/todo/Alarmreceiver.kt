package com.example.wetterbericht.view.util.todo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.wetterbericht.MainActivity

class Alarmreceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //notif channel
        Log.d("alarm","alarm")
        val name = "todo alarm"
        val desc = "test"

        val important = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel("alarmid",name,important)
        channel.description = desc
        val notoficationmanager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notoficationmanager.createNotificationChannel(channel)

        val mbuilder =NotificationCompat.Builder(context!!,"alarmid")
            .setContentTitle("test")
            .setContentText("test")
            .setContentIntent(PendingIntent.getActivity(
                context,
                0,
                Intent(context,MainActivity::class.java),
                0)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

    }
}